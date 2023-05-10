package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
