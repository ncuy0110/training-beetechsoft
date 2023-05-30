package com.beetech.mvcspringboot.controller.publics.product;

import com.beetech.mvcspringboot.service.interfaces.CategoryService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Product controller.
 */
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    /**
     * inject product service
     */
    private final ProductService productService;

    /**
     * inject category service
     */
    private final CategoryService categoryService;

    /**
     * Gets products page.
     *
     * @param request the request
     * @param model   the model
     * @return the products page
     */
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

    /**
     * Gets product details page.
     *
     * @param model     the model
     * @param productId the product id
     * @return the product details page
     */
    @GetMapping("/{productId}")
    public String getProductDetailsPage(Model model, @PathVariable("productId") String productId) {
        model.addAttribute("product", productService.findOne(Long.parseLong(productId)));
        return "user/product/product_details";
    }
}
