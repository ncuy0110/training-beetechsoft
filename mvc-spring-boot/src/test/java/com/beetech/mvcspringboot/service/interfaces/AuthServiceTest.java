package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import com.beetech.mvcspringboot.security.JwtService;
import com.beetech.mvcspringboot.service.implement.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


/**
 * The type Auth service test.
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTest extends BaseServiceTest {
    /**
     * The Auth service.
     */
    @Mock
    AuthServiceImpl authService;
    /**
     * The Authentication manager.
     */
    @Mock
    AuthenticationManager authenticationManager;
    /**
     * The Jwt service.
     */
    @Mock
    JwtService jwtService;

    @Override
    @BeforeEach
    void setUp() {
        super.setUp();
        this.jwtService = mock(JwtService.class);
        String secretKey = "955b46b40b5e48c9896db94aaf9fc0cb955b46b40b5e48c9896db94aaf9fc0cb955b46b40b5e48c9896db94aaf9fc0cb955b46b40b5e48c9896db94aaf9fc0c955b46b40b5e48c9896db94aaf9fc0cbb";
        this.jwtService.setSecretKey(secretKey);
        Long jwtExpirationMs = 86400000L;
        this.jwtService.setJwtExpirationMs(jwtExpirationMs);
        this.authenticationManager = mock(AuthenticationManager.class);
        this.authService = new AuthServiceImpl(this.userRepository, this.authenticationManager, this.jwtService);
        this.userService.register(new RegisterDto(this.username, this.password, this.password));
    }


    /**
     * Test login when valid login info should return access token.
     */
    @Test
    void testLoginWhenValidLoginInfoShouldReturnAccessToken() {
        assertDoesNotThrow(() -> this.authService.login(new LoginDto(this.username, this.password)));
    }

    /**
     * Test login when invalid login info should throw exception.
     */
    @Test
    void testLoginWhenInvalidLoginInfoShouldThrowException() {
        try {
            this.authService.login(new LoginDto("wrong_info", "wrong_info"));
        } catch (ResponseStatusException e) {
            assertEquals("Username or password is incorrect!", e.getMessage());
        }
    }
}