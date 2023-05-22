package com.beetech.mvcspringboot.controller.publics.product;

import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import com.beetech.mvcspringboot.service.interfaces.CategoryService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryServiceImpl categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getProductsPage(HttpServletRequest request, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        try {
            model.addAttribute("products", productService.findByCategory(Long.parseLong(request.getParameter("categoryId"))));
        } catch (Exception e) {
            model.addAttribute("products", productService.findAll());
        }
        return "user/product/index";
    }
}
