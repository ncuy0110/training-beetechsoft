package com.beetech.mvcspringboot.service.implement;

import com.beetech.mvcspringboot.controller.publics.dto.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;
import com.beetech.mvcspringboot.repository.UserRepository;
import com.beetech.mvcspringboot.security.JwtService;
import com.beetech.mvcspringboot.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * The type Auth service.
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    /**
     * Instantiates a new Auth service.
     *
     * @param userRepository        the user repository
     * @param authenticationManager the authentication manager
     * @param jwtService            the jwt service
     */
    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse login(LoginDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }


        var user = userRepository.findUserByUsername(request.getUsername()).orElseThrow();
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(token)
                .build();
    }
}
