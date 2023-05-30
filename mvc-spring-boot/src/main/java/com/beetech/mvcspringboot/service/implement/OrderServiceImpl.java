package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.CartItem;
import com.beetech.mvcspringboot.model.Order;
import com.beetech.mvcspringboot.model.OrderDetail;
import com.beetech.mvcspringboot.repository.OrderRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.beetech.mvcspringboot.service.interfaces.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    @Override
    public Order createOrderFromCart(Long userId) {
        try {
            List<CartItem> cartItemItems = cartService.findAllByUserId(userId);

            Double total = cartService.getTotalByUserId(userId);
            Order order = Order.builder()
                    .user(userRepository
                            .findById(userId)
                            .orElseThrow(() -> new EntityNotFoundException("User not found")))
                    .total(total)
                    .totalAmountAfterDiscount(total)
                    .build();

            List<OrderDetail> orderDetails = cartItemItems
                    .stream()
                    .map(cart -> OrderDetail.builder()
                            .order(order)
                            .product(cart.getProduct())
                            .quantity(cart.getQuantity())
                            .price(cart.getProduct().getPrice())
                            .total(cart.getTotal())
                            .build())
                    .toList();
            order.setOrderDetails(orderDetails);
            return order;
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Create order from cart error: {}", e.getMessage());
            }
            throw new ServerErrorException("Server error: ", e);
        }
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order findByPaymentId(String paymentId) {
        return orderRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }
}
