package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findByCategory(Long categoryId);
    void create(CreateProductDto dto) throws Exception;
    Product findOne(Long productId);
}
