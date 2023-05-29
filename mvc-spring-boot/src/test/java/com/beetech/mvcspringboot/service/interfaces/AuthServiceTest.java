package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import com.beetech.mvcspringboot.security.JwtService;
import com.beetech.mvcspringboot.service.implement.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest extends BaseServiceTest {
    @Mock
    AuthServiceImpl authService;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtService jwtService;

    @BeforeEach
    void setUp() {
        super.setUp();
        jwtService = mock(JwtService.class);
        String SECRET_KEY = "955b46b40b5e48c9896db94aaf9fc0cb955b46b40b5e48c9896db94aaf9fc0cb955b46b40b5e48c9896db94aaf9fc0cb955b46b40b5e48c9896db94aaf9fc0c955b46b40b5e48c9896db94aaf9fc0cbb";
        jwtService.setSECRET_KEY(SECRET_KEY);
        Long jwtExpirationMs = 86400000L;
        jwtService.setJwtExpirationMs(jwtExpirationMs);
        authenticationManager = mock(AuthenticationManager.class);
        authService = new AuthServiceImpl(userRepository, authenticationManager, jwtService);
        userService.register(new RegisterDto(username, password, password));
    }


    @Test
    public void testLogin_whenValidLoginInfo_ShouldReturnAccessToken() {
        assertDoesNotThrow(() -> authService.login(new LoginDto(username, password)));
    }

    @Test
    public void testLogin_WhenInvalidLoginInfo_ShouldThrowException() {
        assertThrows(ResponseStatusException.class, () -> authService.login(new LoginDto("wrong_info", "wrong_info")));
    }
}