package com.beetech.mvcspringboot.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * The type Admin controller.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    /**
     * Gets admin page.
     *
     * @return the admin page
     */
    @GetMapping("")
    public RedirectView getAdminPage() {
        return new RedirectView("/admin/products");
    }
}
