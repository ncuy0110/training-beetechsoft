package com.beetech.mvcspringboot.controller.publics.payment;

import com.beetech.mvcspringboot.controller.publics.payment.dto.CheckoutDto;
import com.beetech.mvcspringboot.model.Order;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.beetech.mvcspringboot.service.interfaces.DiscountService;
import com.beetech.mvcspringboot.service.interfaces.OrderService;
import com.beetech.mvcspringboot.service.interfaces.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaypalController {
    private final PaypalService paypalService;
    private final CartService cartService;
    private final OrderService orderService;
    private final DiscountService discountService;

    public PaypalController(PaypalService paypalService, CartService cartService, OrderService orderService, DiscountService discountService) {
        this.paypalService = paypalService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.discountService = discountService;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping("/pay")
    public String payment(@ModelAttribute CheckoutDto dto, Authentication authentication) {
        try {
            var userDetails = (UserDetails) authentication.getPrincipal();
            Long userId = ((User) userDetails).getId();
            Order order = orderService.createOrderFromCart(userId);
            var totalAmountAfterDiscount = order.getTotal();
            if (!dto.getDiscountId().isBlank()) {
                order = discountService.getChangedAmount(order, Long.parseLong(dto.getDiscountId()));
                totalAmountAfterDiscount = order.getTotalAmountAfterDiscount();
            }
            Payment payment = paypalService.createPayment(
                    totalAmountAfterDiscount,
                    "USD",
                    "paypal",
                    "sale",
                    "test",
                    "http://localhost:8080/cart",
                    "http://localhost:8080/pay/success");

            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    order.setPaymentId(payment.getId());
                    orderService.save(order);
                    cartService.resetCart(userId);

                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("pay/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerID) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerID);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                Order order = orderService.findByPaymentId(paymentId);
                order.setPaid(true);
                order.setPayerId(payerID);
                orderService.save(order);

                return "redirect:/order";
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }
}
