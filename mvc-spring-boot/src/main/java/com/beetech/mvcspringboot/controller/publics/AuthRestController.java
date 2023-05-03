package com.beetech.mvcspringboot.controller.publics;

import com.beetech.mvcspringboot.controller.publics.dto.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import com.beetech.mvcspringboot.service.implement.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {
    private final AuthServiceImpl authService;

    public AuthRestController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
