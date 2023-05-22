package com.beetech.mvcspringboot.controller.admin.product;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import com.beetech.mvcspringboot.service.implement.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;

    public AdminProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String getAdminProductPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product/index";
    }

    @GetMapping("/new")
    public String getCreateProductPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/new_product";
    }

    @PostMapping("/new")
    public String createProduct(@ModelAttribute CreateProductDto product) {
        try {
            System.out.println(product.getImages().get(0).getOriginalFilename());
            productService.create(product);
            return "redirect:/admin/products";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/products/new?error";
    }
}
