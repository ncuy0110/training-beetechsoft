package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.admin.discount.dto.CreateDiscountDto;
import com.beetech.mvcspringboot.model.Discount;
import com.beetech.mvcspringboot.model.Order;

import java.util.List;

public interface DiscountService {
    List<Discount> findAll();
    void save(CreateDiscountDto dto);
    List<Discount> findAllCanApplyNow();
    List<Discount> findAllByUserCart(Long userId);
    Double getChangedAmount(Long userId, Long discountId);
    Order getChangedAmount(Order order, Long discountId);
}
