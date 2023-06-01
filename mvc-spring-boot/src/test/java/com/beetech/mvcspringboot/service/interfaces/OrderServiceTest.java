package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.repository.OrderRepository;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.service.implement.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest extends BaseServiceTest {
    @Mock
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    CartService cartService;

    @BeforeEach
    void setUp() {
        super.setUp();
        orderRepository = mock(OrderRepository.class);
        cartService = mock(CartService.class);
        orderService = new OrderServiceImpl(orderRepository, userRepository, cartService);
    }

    @Test
    void createOrderFromCart() {
    }

    @Test
    void save() {
    }

    @Test
    void findByPaymentId() {
    }

    @Test
    void findAllByUserId() {
    }
}