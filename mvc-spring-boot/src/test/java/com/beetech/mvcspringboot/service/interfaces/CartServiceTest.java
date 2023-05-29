package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.admin.product.dto.CreateProductDto;
import com.beetech.mvcspringboot.model.Cart;
import com.beetech.mvcspringboot.model.CartKeypair;
import com.beetech.mvcspringboot.model.Category;
import com.beetech.mvcspringboot.model.Product;
import com.beetech.mvcspringboot.repository.CartRepository;
import com.beetech.mvcspringboot.service.implement.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CartServiceTest extends BaseServiceTest {
    @Mock
    CartServiceImpl cartService;
    @Mock
    CartRepository cartRepository;

    List<Cart> carts;

    @BeforeEach
    void setUp() {
        super.setUp();
        cartRepository = mock(CartRepository.class);
        carts = new ArrayList<>();


        lenient().when(cartRepository.findByUserIdAndProductId(any(Long.class), any(Long.class)))
                .then((Answer<Optional<Cart>>) invocationOnMock -> {
                    Long userId = invocationOnMock.getArgument(0);
                    Long productId = invocationOnMock.getArgument(1);
                    return carts.stream()
                            .filter(cart
                                    -> cart.getId().getProductId() == productId && cart.getId().getUserid() == userId)
                            .findFirst();
                });

        lenient().when(cartRepository.findAllByUserId(any(Long.class)))
                .then((Answer<List<Cart>>) invocationOnMock -> {
                    Long userId = invocationOnMock.getArgument(0);
                    return carts.stream().filter(cart ->
                            cart.getId().getUserid() == userId).toList();
                });

        cartService = new CartServiceImpl(cartRepository);
    }

    @Test
    void findAllByUserId() {
        var actual = cartService.findAllByUserId(1L);
        assertEquals(1, actual.size());
        assertEquals(carts.get(0), actual.get(0));
    }

    @Test
    void findOneByUserAndProduct() {
        Cart actual = cartService.findOneByUserAndProduct(1L, 1L);
        assertEquals(carts.get(0), actual);
    }

    @Test
    void getTotalByUserId() {
        Product product = Product.builder()
                .id(1L)
                .price(1.0)
                .name("test product 2")
                .category(new Category())
                .quantity(100L)
                .description("test")
                .images(new ArrayList<>())
                .build();

        carts.add(Cart.builder()
                .quantity(1L)
                .id(new CartKeypair(1L, 1L))
                .product(product)
                .build());
        Double actual = cartService.getTotalByUserId(1L);
        assertEquals(1.0, actual);
    }

    @Test
    void addToCart() {
        Product product = Product.builder()
                .id(1L)
                .price(1.0)
                .name("test product 2")
                .category(new Category())
                .quantity(100L)
                .description("test")
                .images(new ArrayList<>())
                .build();

        int size = carts.size();
        carts.add(Cart.builder()
                .quantity(1L)
                .id(new CartKeypair(1L, 1L))
                .product(product)
                .build());
        assertEquals(size+1, carts.size());
    }

}