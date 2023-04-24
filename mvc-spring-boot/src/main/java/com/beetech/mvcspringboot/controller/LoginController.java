package com.beetech.mvcspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Login controller.
 */
@Controller
public class LoginController {
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
}
