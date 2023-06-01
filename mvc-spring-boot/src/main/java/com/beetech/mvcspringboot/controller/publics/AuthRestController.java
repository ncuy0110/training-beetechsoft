package com.beetech.mvcspringboot.controller.publics;

import com.beetech.mvcspringboot.controller.publics.dto.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import com.beetech.mvcspringboot.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    /**
     * inject auth service
     */
    private final AuthService authService;

    /**
     * Login response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid LoginDto request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
