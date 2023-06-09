package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.controller.admin.product.dto.ProductCsvDto;
import com.beetech.mvcspringboot.model.Product;
import com.beetech.mvcspringboot.repository.CategoryRepository;
import com.beetech.mvcspringboot.repository.ProductRepository;
import com.beetech.mvcspringboot.service.interfaces.ProductImageService;
import com.beetech.mvcspringboot.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductImageService productImageService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(CreateProductDto dto) {
        try {
            Product product = productRepository.save(new Product(dto.getName(),
                    dto.getPrice(),
                    dto.getQuantity(),
                    dto.getDescription(),
                    categoryRepository.findById(dto.getCategoryId()).orElseThrow(() ->
                            new Exception("Category not found"))));
            productImageService.create(dto.getImages(), product);
        } catch (Exception e) {
            throw new ServerErrorException("Server error: ", e);
        }
    }

    @Override
    public void createByCsv(List<ProductCsvDto> productCsvDtoList) {
        List<CreateProductDto> createProductDtoList = productCsvDtoList
                .stream()
                .map(CreateProductDto::new)
                .toList();
        createProductDtoList.forEach(this::create);
    }

    public Product findOne(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> findAllByIds(List<Long> ids) {
        return productRepository.findAllByIdIn(ids);
    }
}
