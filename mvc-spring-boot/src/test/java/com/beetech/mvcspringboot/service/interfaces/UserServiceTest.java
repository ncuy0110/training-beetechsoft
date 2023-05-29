package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserServiceTest extends BaseServiceTest {
    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName("Test for register method")
    public void testRegister_withValidInfo_shouldReturnNewUser() {
        String username = "test_username";
        String password = "test1";
        RegisterDto registerDto = new RegisterDto(username, password, password);
        assertNotNull(userService.register(registerDto));

        var optionalUser = userRepository.findUserByUsername(username);
        assertTrue(optionalUser.isPresent());
        assertTrue(passwordEncoder.matches(password, optionalUser.get().getPassword()));
    }
}