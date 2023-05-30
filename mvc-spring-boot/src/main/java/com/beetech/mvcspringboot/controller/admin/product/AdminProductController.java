package com.beetech.mvcspringboot.controller.admin.product;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.controller.admin.product.dto.ProductCsvDto;
import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import com.beetech.mvcspringboot.service.implement.ProductServiceImpl;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * The type Admin product controller.
 */
@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminProductController.class);
    /**
     * inject product service
     */
    private final ProductServiceImpl productService;
    /**
     * inject category service
     */
    private final CategoryServiceImpl categoryService;

    /**
     * Gets admin product page.
     *
     * @param model the model
     * @return the admin product page
     */
    @GetMapping("")
    public String getAdminProductPage(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/product/index";
    }

    /**
     * Gets create product page.
     *
     * @param model the model
     * @return the create product page
     */
    @GetMapping("/new")
    public String getCreateProductPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/product/new_product";
    }

    /**
     * Create product string.
     *
     * @param product the product
     * @return the string
     */
    @PostMapping("/new")
    public String createProduct(@ModelAttribute CreateProductDto product) {
        try {
            productService.create(product);
            return "redirect:/admin/products";
        } catch (Exception e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Create product error: {}", e.getMessage());
            }
        }
        return "redirect:/admin/products/new?error";
    }

    /**
     * Gets upload csv page.
     *
     * @return the upload csv page
     */
    @GetMapping("/upload-csv")
    public String getUploadCsvPage() {
        return "admin/product/upload_csv";
    }

    /**
     * Upload product in csv file string.
     *
     * @param csvFile the csv file
     * @return the string
     */
    @PostMapping("/upload-csv")
    public String uploadProductInCsvFile(@RequestParam("csvFile") MultipartFile csvFile) {
        try (Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "Shift_JIS"))) {
            CsvToBean<ProductCsvDto> csvToBean = new CsvToBeanBuilder<ProductCsvDto>(reader)
                    .withType(ProductCsvDto.class)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<ProductCsvDto> productCsvDtoList = csvToBean.parse();
            productService.createByCsv(productCsvDtoList);
            return "redirect:/admin/products";
        } catch (IOException e) {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Create product by CSV file error: {}", e.getMessage());
            }
        }
        return "redirect:/admin/products/new?error";
    }
}
