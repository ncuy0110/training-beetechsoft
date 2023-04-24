package com.beetech.mvcspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The type Home controller.
 */
@Controller
public class HomeController {
    /**
     * Gets home page.
     *
     * @return the home page
     */
    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }
}
