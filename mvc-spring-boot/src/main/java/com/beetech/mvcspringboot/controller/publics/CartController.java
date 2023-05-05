package com.beetech.mvcspringboot.controller.publics;

import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public String getCartPage(
            Model model,
            Principal principal,
            @CookieValue(value = "carts", defaultValue = "") String cartsCookie
    ) {
        Authentication authentication = (Authentication) principal;
        if (authentication != null && authentication.isAuthenticated()) {
            var userDetails = (UserDetails) authentication.getPrincipal();
            Long userId = ((User) userDetails).getId();
            model.addAttribute("carts", cartService.findAllByUserId(userId));
        } else if (!cartsCookie.equals("")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<Long, Long> carts = objectMapper.readValue(cartsCookie, new TypeReference<Map<Long, Long>>() {
                });
//                carts.forEach();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return "user/cart/index";
    }
}
