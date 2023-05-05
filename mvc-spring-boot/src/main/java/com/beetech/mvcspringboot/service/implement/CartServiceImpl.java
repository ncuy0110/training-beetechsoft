package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.CartKeypair;
import com.beetech.mvcspringboot.repository.CartRepository;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void synchronizeCart(Long userId, Map<Long, Long> carts) {
        carts.forEach((productId, quantity) -> {
            try {
                var optionalCart = cartRepository.findById(new CartKeypair(userId, productId));
                if (optionalCart.isEmpty()) {
                    Cart cart = new Cart(new CartKeypair(userId, productId), quantity);
                    cartRepository.save(cart);
                } else {
                    Cart cart = optionalCart.get();
                    cart.setQuantity(cart.getQuantity() + quantity);
                    cartRepository.save(cart);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }
}
