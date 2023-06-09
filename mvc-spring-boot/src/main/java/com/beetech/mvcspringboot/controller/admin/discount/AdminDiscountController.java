package com.beetech.mvcspringboot.controller.admin.discount;

import com.beetech.mvcspringboot.controller.admin.discount.dto.CreateDiscountDto;
import com.beetech.mvcspringboot.service.interfaces.DiscountService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Admin discount controller.
 */
@Controller
@RequestMapping("/admin/discounts")
@RequiredArgsConstructor
public class AdminDiscountController {
    /**
     * inject discount service
     */
    private final DiscountService discountService;
    /**
     * inject product service
     */
    private final ProductService productService;

    /**
     * Gets discount page.
     *
     * @param model the model
     * @return the discount page
     */
    @GetMapping("")
    public String getDiscountPage(Model model) {
        model.addAttribute("discounts", discountService.findAll());
        return "admin/discount/index";
    }

    /**
     * Gets new discount page.
     *
     * @param model the model
     * @return the new discount page
     */
    @GetMapping("/new")
    public String getNewDiscountPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/discount/new_discount";
    }

    /**
     * Create discount string.
     *
     * @param discountDto the discount dto
     * @return the string
     */
    @PostMapping("/new")
    public String createDiscount(@ModelAttribute CreateDiscountDto discountDto) {
        try {
            discountDto.validate();
            discountService.save(discountDto);
            return "redirect:/admin/discounts?create_success";
        } catch (Exception e) {
            return "redirect:/admin/discounts/new?error=" + e.getMessage();
        }
    }
}
