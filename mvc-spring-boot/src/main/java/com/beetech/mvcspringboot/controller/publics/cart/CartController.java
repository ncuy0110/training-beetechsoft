package com.beetech.mvcspringboot.controller.publics.cart;

import com.beetech.mvcspringboot.controller.publics.cart.dto.CartItem;
import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.Discount;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.beetech.mvcspringboot.service.interfaces.DiscountService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final DiscountService discountService;

    public CartController(CartService cartService, ProductService productService, DiscountService discountService) {
        this.cartService = cartService;
        this.productService = productService;
        this.discountService = discountService;
    }

    @GetMapping("")
    public String getCartPage(
            Authentication authentication,
            Model model,
            @CookieValue(value = "carts", defaultValue = "") String cartsCookie
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            var userDetails = (UserDetails) authentication.getPrincipal();
            Long userId = ((User) userDetails).getId();
            List<Cart> carts = cartService.findAllByUserId(userId);
            List<Discount> discounts = discountService.findAllByUserCart(userId);
            discounts.forEach(System.out::println);
            model.addAttribute("discounts", discounts);
            model.addAttribute("carts", carts);
        } else if (!cartsCookie.equals("")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<Long, Long> carts = objectMapper.readValue(cartsCookie, new TypeReference<>() {
                });
                List<Long> productIds = new ArrayList<>(carts.keySet());
                List<CartItem> cartItems = productService.findAllByIds(productIds)
                        .stream()
                        .map(product -> new CartItem(product, carts.get(product.getId())))
                        .collect(Collectors.toList());
                model.addAttribute("cartItems", cartItems);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            model.addAttribute("cartItems", new ArrayList<CartItem>());
        }
        return "user/cart/index";
    }
}