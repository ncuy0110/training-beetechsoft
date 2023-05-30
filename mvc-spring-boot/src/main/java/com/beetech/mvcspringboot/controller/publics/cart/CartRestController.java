package com.beetech.mvcspringboot.controller.publics.cart;

import com.beetech.mvcspringboot.controller.publics.cart.dto.AddingCartItemDto;
import com.beetech.mvcspringboot.controller.publics.cart.dto.SetCartItemDto;
import com.beetech.mvcspringboot.model.CartItem;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * The type Cart rest controller.
 */
@Controller
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartRestController {
    /**
     * inject cart service
     */
    private final CartService cartService;

    /**
     * Add to cart response entity.
     *
     * @param authentication the authentication
     * @param cartItemDto    the cart item dto
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity<Object> addToCart(Authentication authentication, @RequestBody AddingCartItemDto cartItemDto) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        this.cartService.addToCart(((User) userDetails).getId(), cartItemDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Gets all cart items.
     *
     * @param authentication the authentication
     * @return the all cart items
     */
    @GetMapping("")
    public ResponseEntity<List<CartItem>> getAllCartItems(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var cartItems = this.cartService.findAllByUserId(((User) userDetails).getId());
        return ResponseEntity.ok(cartItems);
    }

    /**
     * Sets quantity.
     *
     * @param authentication the authentication
     * @param cartItemDto    the cart item dto
     * @return the quantity
     */
    @PostMapping("/quantity")
    public ResponseEntity<Object> setQuantity(Authentication authentication, @RequestBody SetCartItemDto cartItemDto) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var user = (User) userDetails;
        this.cartService.setQuantity(user.getId(), cartItemDto);
        return ResponseEntity.ok().build();
    }
}
