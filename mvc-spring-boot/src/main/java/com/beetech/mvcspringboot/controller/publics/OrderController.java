package com.beetech.mvcspringboot.controller.publics;

import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public String getAllOrder(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("orders", orderService.findAllByUserId(user.getId()));
        return "user/order/index";
    }
}
