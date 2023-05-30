package com.beetech.mvcspringboot.repository;

import com.beetech.mvcspringboot.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
