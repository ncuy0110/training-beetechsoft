package com.beetech.mvcspringboot.controller.publics;

import com.beetech.mvcspringboot.model.User;
import com.beetech.mvcspringboot.service.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Order controller.
 */
@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    /**
     * inject order service
     */
    private final OrderService orderService;

    /**
     * Gets all order.
     *
     * @param model          the model
     * @param authentication the authentication
     * @return the all order
     */
    @GetMapping("")
    public String getAllOrder(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("orders", orderService.findAllByUserId(user.getId()));
        return "user/order/index";
    }
}
