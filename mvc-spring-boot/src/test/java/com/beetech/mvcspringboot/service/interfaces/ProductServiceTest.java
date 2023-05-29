package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.controller.admin.product.dto.ProductCsvDto;
import com.beetech.mvcspringboot.model.Product;
import com.beetech.mvcspringboot.repository.ProductRepository;
import com.beetech.mvcspringboot.service.implement.ProductImageServiceImpl;
import com.beetech.mvcspringboot.service.implement.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest extends BaseServiceTest {
    @Mock
    List<Product> products;
    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductImageServiceImpl productImageService;

    @BeforeEach
    void setUp() {
        super.setUp();
        products = new ArrayList<>();
        productRepository = mock(ProductRepository.class);

        // find alll
        lenient().when(productRepository.findAll()).thenReturn(products);

        // find all by category id
        lenient().when(productRepository.findAllByCategoryId(any(Long.class)))
                .then((Answer<List<Product>>) invocationOnMock -> {
                    Long categoryId = invocationOnMock.getArgument(0);
                    return products.stream().filter(product -> product.getCategory().getId() == categoryId).toList();
                });

        // find by id
        lenient().when(productRepository.findById(any(Long.class))).then((Answer<Optional<Product>>) invocationOnMock -> {
            Long id = invocationOnMock.getArgument(0);
            return products.stream().filter(product
                    -> product.getId() == id).findFirst();
        });

        // save product
        lenient().when(productRepository.save(any(Product.class))).then(new Answer<Product>() {
            long sequence = 1;

            @Override
            public Product answer(InvocationOnMock invocationOnMock) throws Throwable {
                Product product = invocationOnMock.getArgument(0);
                product.setId(sequence++);
                products.add(product);
                return product;
            }
        });

        // find by list id
        lenient().when(productRepository.findAllByIdIn(any())).then((Answer<List<Product>>) invocationOnMock -> {
            List<Long> ids = invocationOnMock.getArgument(0);
            return products.stream()
                    .filter(product
                            -> ids.contains(product.getId()))
                    .toList();
        });

        // init product
        productRepository.save(Product.builder()
                .price(1.0)
                .name("test product 1")
                .category(categoryRepository
                        .findById(1L)
                        .orElseThrow())
                .quantity(100L)
                .description("test")
                .build());
        productRepository.save(Product.builder()
                .price(1.0)
                .name("test product 2")
                .category(categoryRepository
                        .findById(1L)
                        .orElseThrow())
                .quantity(100L)
                .description("test")
                .build());
        productRepository.save(Product.builder()
                .price(1.0)
                .name("test product 3")
                .category(categoryRepository
                        .findById(1L)
                        .orElseThrow())
                .quantity(100L)
                .description("test")
                .build());
        productService = new ProductServiceImpl(productRepository, categoryRepository, productImageService);
    }

    @Test
    void findAll_ShouldReturnListProduct() {
        var result = productService.findAll();
        assertEquals(result.size(), products.size());
    }

    @Test
    void findByCategory() {
        long categoryId = 1;
        var actualProducts = productService.findByCategory(categoryId);
        assertTrue(actualProducts.size() > 0);
    }

    @Test
    void createProduct_WhenValidInfo() {
        var productDto = CreateProductDto.builder()
                .price(1.0)
                .name("test product 2")
                .categoryId(1L)
                .quantity(100L)
                .description("test")
                .images(new ArrayList<>())
                .build();
        var size = products.size();
        productService.create(productDto);
        assertEquals(size + 1, products.size());
        assertEquals(productDto.getName(), products.get(size).getName());
    }

    @Test
    void createByCsv() {
        List<ProductCsvDto> dtoList = new ArrayList<>();
        dtoList.add(ProductCsvDto.builder()
                .price(1.0)
                .name("test product 1")
                .categoryId(1L)
                .quantity(100L)
                .description("test")
                .build()
        );
        dtoList.add(ProductCsvDto.builder()
                .price(1.0)
                .name("test product 2")
                .categoryId(1L)
                .quantity(100L)
                .description("test")
                .build()
        );

        var size = products.size();
        productService.createByCsv(dtoList);
        assertEquals(size, products.size() - 2);
    }

    @Test
    void findOne() {
        var expected = products.get(0);
        var actual = productService.findOne(1L);
        assertEquals(expected, actual);
    }

    @Test
    void findAllByIds() {
        var actual = productService.findAllByIds(new ArrayList<>(List.of(1L, 3L)));
        assertEquals(2, actual.size() );
        assertEquals(products.get(0), actual.get(0));
        assertEquals(products.get(2), actual.get(1));
    }
}