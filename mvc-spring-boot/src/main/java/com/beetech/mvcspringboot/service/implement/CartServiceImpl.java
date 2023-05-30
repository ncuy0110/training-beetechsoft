package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.controller.publics.cart.dto.AddingCartItemDto;
import com.beetech.mvcspringboot.controller.publics.cart.dto.SetCartItemDto;
import com.beetech.mvcspringboot.model.CartItem;
import com.beetech.mvcspringboot.model.CartKeypair;
import com.beetech.mvcspringboot.repository.CartRepository;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The type Cart service.
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;

    @Override
    public void synchronizeCart(Long userId, Map<Long, Long> carts) {
        carts.forEach((productId, quantity) -> {
            try {
                var optionalCart = cartRepository.findById(new CartKeypair(userId, productId));
                CartItem cartItem;
                if (optionalCart.isEmpty()) {
                    cartItem = new CartItem(new CartKeypair(userId, productId), quantity);
                } else {
                    cartItem = optionalCart.get();
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                }
                cartRepository.save(cartItem);
            } catch (Exception e) {
                if (LOGGER.isErrorEnabled()) {
                    LOGGER.error("Sync cart error: {}", e.getMessage());
                }
            }
        });
    }

    @Override
    public List<CartItem> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    @Override
    public CartItem findOneByUserAndProduct(Long userId, Long productId) {
        Optional<CartItem> optionalCart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (optionalCart.isEmpty()) {
            throw new EntityNotFoundException("Cart item not found!");
        }
        return optionalCart.get();
    }

    @Override
    public Double getTotalByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream().mapToDouble(CartItem::getTotal)
                .sum();
    }

    @Override
    public void addToCart(Long userId, AddingCartItemDto cartItemDto) {
        CartItem cartItem;
        Optional<CartItem> cartItemOptional = cartRepository.findByUserIdAndProductId(userId, cartItemDto.getProductId());
        if (cartItemOptional.isEmpty()) {
            cartItem = CartItem.builder()
                    .id(new CartKeypair(userId, cartItemDto.getProductId()))
                    .quantity(cartItemDto.getQuantity())
                    .build();
        } else {
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        }
        cartRepository.save(cartItem);
    }

    @Override
    public void setQuantity(Long userId, SetCartItemDto cartItemDto) {
        CartItem cartItem = cartRepository.findByUserIdAndProductId(userId, cartItemDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found!"));
        cartItem.setQuantity(cartItem.getQuantity());
        cartRepository.save(cartItem);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void resetCart(Long userId) {
        cartRepository.resetCartByUserId(userId);
    }
}
