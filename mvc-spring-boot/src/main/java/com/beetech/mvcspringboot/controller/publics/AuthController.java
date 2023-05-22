package com.beetech.mvcspringboot.controller.publics;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Login controller.
 */
@Controller
public class AuthController {
    /**
     * Gets login page.
     *
     * @param model the model
     * @return the login page
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }
}
