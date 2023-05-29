package com.beetech.mvcspringboot.controller.admin.product;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.controller.admin.product.dto.ProductCsvDto;
import com.beetech.mvcspringboot.service.implement.CategoryServiceImpl;
import com.beetech.mvcspringboot.service.implement.ProductServiceImpl;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class AdminProductController {
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;

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
            productService.create(product);
            return "redirect:/admin/products";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/products/new?error";
    }

    @GetMapping("/upload-csv")
    public String getUploadCsvPage() {
        return "admin/product/upload_csv";
    }

    @PostMapping("/upload-csv")
    public String uploadProductInCsvFile(@RequestParam("csvFile") MultipartFile csvFile) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), "Shift_JIS"));
            CsvToBean<ProductCsvDto> csvToBean = new CsvToBeanBuilder<ProductCsvDto>(reader)
                    .withType(ProductCsvDto.class)
                    .withIgnoreEmptyLine(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<ProductCsvDto> productCsvDtoList = csvToBean.parse();
            System.out.println(productCsvDtoList.size());
            productService.createByCsv(productCsvDtoList);
            return "redirect:/admin/products";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/products/new?error";
    }
}
