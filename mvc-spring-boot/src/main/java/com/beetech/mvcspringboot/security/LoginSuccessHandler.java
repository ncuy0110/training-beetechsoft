package com.beetech.mvcspringboot.security;

import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.CartService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final CartService cartService;

    public LoginSuccessHandler(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = ((User) userDetails).getId();
        var cartsCookie = WebUtils.getCookie(request, "carts");
        if (cartsCookie != null) {
            String cartsJsonString = URLDecoder.decode(cartsCookie.getValue(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<Long, Long> carts = objectMapper.readValue(cartsJsonString, new TypeReference<Map<Long, Long>>() {
            });
            cartService.synchronizeCart(userId, carts);
            // create a cookie
            Cookie cookie = new Cookie("carts", null);
            cookie.setMaxAge(0);
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");

            //add cookie to response
            response.addCookie(cookie);
        }

        response.sendRedirect("/");
    }
}
