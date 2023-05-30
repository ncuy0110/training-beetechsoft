package com.beetech.mvcspringboot.configuration;

import com.beetech.mvcspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * The type Application config.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    /**
     * inject user repository
     */
    private final UserRepository userRepository;


    /**
     * User details service user details service.
     *
     * @return the user details service
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}
