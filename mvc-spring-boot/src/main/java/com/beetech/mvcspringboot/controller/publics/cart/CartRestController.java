package com.beetech.mvcspringboot.controller.publics.cart;

import com.beetech.mvcspringboot.controller.publics.cart.dto.AddingCartItemDto;
import com.beetech.mvcspringboot.controller.publics.cart.dto.SetCartItemDto;
import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/cart")
public class CartRestController {
    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("")
    public ResponseEntity<?> addToCart(Authentication authentication, @RequestBody AddingCartItemDto cartItemDto) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        cartService.addToCart(((User) userDetails).getId(), cartItemDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Cart>> getAllCartItems(Authentication authentication) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var cartItems = cartService.findAllByUserId(((User) userDetails).getId());
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/quantity")
    public ResponseEntity<?> setQuantity(Authentication authentication, @RequestBody SetCartItemDto cartItemDto) {
        var userDetails = (UserDetails) authentication.getPrincipal();
        var user = (User) userDetails;
        cartService.setQuantity(user.getId(), cartItemDto);
        return ResponseEntity.ok().build();
    }
}
