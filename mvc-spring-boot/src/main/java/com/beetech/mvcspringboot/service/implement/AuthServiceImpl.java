package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.controller.publics.dto.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.security.JwtService;
import com.beetech.mvcspringboot.service.interfaces.AuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Auth service.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse login(LoginDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
            var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow(()
                    -> new EntityNotFoundException("User not found!"));

            var token = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(token)
                    .build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect!");
        }
    }
}
