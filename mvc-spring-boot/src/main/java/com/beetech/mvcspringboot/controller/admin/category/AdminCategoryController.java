package com.beetech.mvcspringboot.controller.admin.category;

import com.beetech.mvcspringboot.controller.admin.category.dto.CreateCategoryDto;
import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping("")
    public String getCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/category/index";
    }

    @GetMapping("/new")
    public String getCreateCategoryPage() {
        return "admin/category/new_category";
    }

    @PostMapping("/new")
    public String createCategory(@ModelAttribute CreateCategoryDto category) {
        try{
            System.out.println(category.getName());
            categoryService.create(category.getName());
            return "redirect:/admin/categories";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/categories/new?error";
    }
}
