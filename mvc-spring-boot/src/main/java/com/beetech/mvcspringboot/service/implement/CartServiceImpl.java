package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.controller.publics.cart.dto.AddingCartItemDto;
import com.beetech.mvcspringboot.controller.publics.cart.dto.SetCartItemDto;
import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.CartKeypair;
import com.beetech.mvcspringboot.repository.CartRepository;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import jakarta.persistence.EntityNotFoundException;
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
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    /**
     * Instantiates a new Cart service.
     *
     * @param cartRepository the cart repository
     */
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void synchronizeCart(Long userId, Map<Long, Long> carts) {
        carts.forEach((productId, quantity) -> {
            try {
                var optionalCart = cartRepository.findById(new CartKeypair(userId, productId));
                Cart cart;
                if (optionalCart.isEmpty()) {
                    cart = new Cart(new CartKeypair(userId, productId), quantity);
                } else {
                    cart = optionalCart.get();
                    cart.setQuantity(cart.getQuantity() + quantity);
                }
                cartRepository.save(cart);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public List<Cart> findAllByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    @Override
    public Cart findOneByUserAndProduct(Long userId, Long productId) {
        Optional<Cart> optionalCart = cartRepository.findByUserIdAndProductId(userId, productId);
        if (optionalCart.isEmpty()) {
            throw new EntityNotFoundException("Cart item not found!");
        }
        return optionalCart.get();
    }

    @Override
    public Double getTotalByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId)
                .stream().mapToDouble(Cart::getTotal)
                .sum();
    }

    @Override
    public void addToCart(Long userId, AddingCartItemDto cartItemDto) {
        Cart cartItem;
        Optional<Cart> cartItemOptional = cartRepository.findByUserIdAndProductId(userId, cartItemDto.getProductId());
        if (cartItemOptional.isEmpty()) {
            cartItem = Cart.builder()
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
        Cart cartItem = cartRepository.findByUserIdAndProductId(userId, cartItemDto.getProductId())
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
