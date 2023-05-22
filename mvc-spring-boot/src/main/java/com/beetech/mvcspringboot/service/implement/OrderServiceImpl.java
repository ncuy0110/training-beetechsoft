package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.Order;
import com.beetech.mvcspringboot.model.OrderDetail;
import com.beetech.mvcspringboot.repository.OrderRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.beetech.mvcspringboot.service.interfaces.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartService cartService;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartService = cartService;
    }

    @Override
    public Order createOrderFromCart(Long userId) {
        try {
            List<Cart> cartItems = cartService.findAllByUserId(userId);
            Long total = cartService.getTotalByUserId(userId);
            Order order = Order.builder()
                    .user(userRepository
                            .findById(userId)
                            .orElseThrow(() -> new EntityNotFoundException("User not found")))
                    .total(total)
                    .build();

            List<OrderDetail> orderDetails = cartItems
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
            e.printStackTrace();
            throw new RuntimeException();
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
