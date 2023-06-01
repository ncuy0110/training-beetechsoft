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
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerErrorException;

/**
 * The type Paypal controller.
 */
@Controller
@RequiredArgsConstructor
public class PaypalController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PaypalController.class);

    /**
     * inject PayPal service
     */
    private final PaypalService paypalService;

    /**
     * inject cart service
     */
    private final CartService cartService;

    /**
     * inject order service
     */
    private final OrderService orderService;

    /**
     * inject discount service
     */
    private final DiscountService discountService;

    /**
     * Payment string.
     *
     * @param dto            the dto
     * @param authentication the authentication
     * @return the string
     */
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
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Payment error: {}", e.getMessage());
            }
            throw new ServerErrorException("Server error", e);
        }
        return "redirect:/";
    }

    /**
     * Success pay string.
     *
     * @param paymentId the payment id
     * @param payerID   the payer id
     * @return the string
     */
    @GetMapping("pay/success")
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerID) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerID);
            if (payment.getState().equals("approved")) {
                Order order = orderService.findByPaymentId(paymentId);
                order.setPaid(true);
                order.setPayerId(payerID);
                orderService.save(order);

                return "redirect:/order";
            }
        } catch (PayPalRESTException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Error: {}", e.getMessage());
            }
        }
        return "redirect:/";
    }
}
