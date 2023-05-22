package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.cart.dto.AddingCartItemDto;
import com.beetech.mvcspringboot.controller.publics.cart.dto.SetCartItemDto;
import com.beetech.mvcspringboot.model.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    void synchronizeCart(Long userId, Map<Long, Long> carts);
    List<Cart> findAllByUserId(Long userId);
    Long getTotalByUserId(Long userId);
    void addToCart(Long userId, AddingCartItemDto cartItemDto);
    void setQuantity(Long userId, SetCartItemDto cartItemDto);
    void resetCart(Long userId);
}
