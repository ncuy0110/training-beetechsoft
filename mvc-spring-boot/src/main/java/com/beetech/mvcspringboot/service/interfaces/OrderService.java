package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrderFromCart(Long userId);

    void save(Order order);

    Order findByPaymentId(String paymentId);

    List<Order> findAllByUserId(Long userId);
}
