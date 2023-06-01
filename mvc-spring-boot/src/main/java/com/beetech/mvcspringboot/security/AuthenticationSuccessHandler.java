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
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final CartService cartService;
    private final JwtService jwtService;


    public AuthenticationSuccessHandler(CartService cartService, JwtService jwtService) {
        this.cartService = cartService;
        this.jwtService = jwtService;
        RequestCache requestCache = new CookieRequestCache();
        super.setRequestCache(requestCache);
        super.setDefaultTargetUrl("/");
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var userDetails = (UserDetails) authentication.getPrincipal();
        Long userId = ((User) userDetails).getId();
        var cartsCookie = WebUtils.getCookie(request, "carts");
        if (cartsCookie != null) {
            String cartsJsonString = URLDecoder.decode(cartsCookie.getValue(), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            ConcurrentHashMap<Long, Long> carts = objectMapper.readValue(cartsJsonString, new TypeReference<>() {
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

        var token = jwtService.generateToken(userDetails);
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setMaxAge(24 * 60 * 60 * 1000);
        response.addCookie(cookie);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
