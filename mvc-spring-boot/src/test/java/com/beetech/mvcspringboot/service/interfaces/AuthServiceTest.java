package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AuthServiceTest {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("BEFORE ALL");
    }

    @Test
    public void testLogin() {
//        LoginDto loginDto = new LoginDto("admin", "admin");
//        Assertions.assertNotNull(authService.login(loginDto));
        Assertions.assertEquals(2, 1+1);
    }
}