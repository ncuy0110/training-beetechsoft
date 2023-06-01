package com.beetech.mvcspringboot.controller.publics.cart;

import com.beetech.mvcspringboot.model.CartItem;
import com.beetech.mvcspringboot.model.Discount;
import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.beetech.mvcspringboot.service.interfaces.DiscountService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Cart controller.
 */
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    /**
     * inject cart service
     */
    private final CartService cartService;
    /**
     * inject product service
     */
    private final ProductService productService;
    /**
     * inject discount service
     */
    private final DiscountService discountService;


    /**
     * Gets cart page.
     *
     * @param authentication the authentication
     * @param model          the model
     * @param cartsCookie    the carts cookie
     * @return the cart page
     */
    @GetMapping("")
    public String getCartPage(
            Authentication authentication,
            Model model,
            @CookieValue(value = "carts", defaultValue = "") String cartsCookie
    ) {
        if (authentication != null && authentication.isAuthenticated()) {
            var userDetails = (UserDetails) authentication.getPrincipal();
            Long userId = ((User) userDetails).getId();
            List<CartItem> cartItems = this.cartService.findAllByUserId(userId);
            List<Discount> discounts = this.discountService.findAllByUserCart(userId);
            model.addAttribute("discounts", discounts);
            model.addAttribute("carts", cartItems);
        } else if (!cartsCookie.equals("")) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                ConcurrentHashMap<Long, Long> carts = objectMapper.readValue(cartsCookie, new TypeReference<>() {
                });
                List<Long> productIds = new ArrayList<>(carts.keySet());
                List<com.beetech.mvcspringboot.controller.publics.cart.dto.CartItem> cartItemItems = this.productService.findAllByIds(productIds)
                        .stream()
                        .map(product -> new com.beetech.mvcspringboot.controller.publics.cart.dto.CartItem(product, carts.get(product.getId())))
                        .toList();
                model.addAttribute("cartItems", cartItemItems);
            } catch (JsonProcessingException e) {
                throw new ServerErrorException("Server error", e);
            }
        } else {
            model.addAttribute("cartItems", new ArrayList<com.beetech.mvcspringboot.controller.publics.cart.dto.CartItem>());
        }
        return "user/cart/index";
    }
}
