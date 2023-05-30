package com.beetech.mvcspringboot.controller.admin.category;

import com.beetech.mvcspringboot.controller.admin.category.dto.CreateCategoryDto;
import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Admin category controller.
 */
@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {
    /**
     * logger for this class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminCategoryController.class);
    /**
     * inject category service
     */
    private final CategoryServiceImpl categoryService;

    /**
     * Gets category page.
     *
     * @param model the model
     * @return the category page
     */
    @GetMapping("")
    public String getCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/category/index";
    }

    /**
     * Gets create category page.
     *
     * @return the create category page
     */
    @GetMapping("/new")
    public String getCreateCategoryPage() {
        return "admin/category/new_category";
    }

    /**
     * Create category string.
     *
     * @param category the category
     * @return the string
     */
    @PostMapping("/new")
    public String createCategory(@ModelAttribute CreateCategoryDto category) {
        try {
            categoryService.create(category.getName());
            return "redirect:/admin/categories";
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Create category error: {}", e.getMessage());
            }
        }
        return "redirect:/admin/categories/new?error";
    }
}
