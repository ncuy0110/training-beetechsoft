package com.beetech.mvcspringboot.controller.publics;

import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import com.beetech.mvcspringboot.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The type Login controller.
 */
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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

    /**
     * Gets register page.
     *
     * @return the register page
     */
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    /**
     * Register string.
     *
     * @param registerDto the register dto
     * @return the string
     */
    @PostMapping("/register")
    public String register(
            @ModelAttribute @Valid RegisterDto registerDto
    ) {
        try {
            userService.register(registerDto);
            return "redirect:/login?register_success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/register?error";
    }
}
