package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByPaymentId(String paymentId);

    List<Order> findAllByUserId(Long userId);
}
