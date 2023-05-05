package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.model.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    void synchronizeCart(Long userId, Map<Long, Long> carts);
    List<Cart> findAllByUserId(Long userId);
}
