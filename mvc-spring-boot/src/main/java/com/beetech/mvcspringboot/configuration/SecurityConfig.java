package com.beetech.mvcspringboot.configuration;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.security.JwtAuthenticationFilter;
import com.beetech.mvcspringboot.security.LoginSuccessHandler;
import com.beetech.mvcspringboot.service.implement.UserServiceImpl;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserServiceImpl userService;
    private final CustomPasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final LoginSuccessHandler loginSuccessHandler;

    /**
     * Instantiates a new Security config.
     *
     * @param userService             the user service
     * @param passwordEncoder         the password encoder
     * @param jwtAuthenticationFilter jwt filter
     * @param loginSuccessHandler     handler on login success
     */
    public SecurityConfig(
            UserServiceImpl userService,
            CustomPasswordEncoder passwordEncoder,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            LoginSuccessHandler loginSuccessHandler) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder).and().build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/admin/**")
                    .hasAuthority(RoleEnum.ADMIN.toString()).and();

            request.requestMatchers("/api/v1/cart/**")
                    .hasAuthority(RoleEnum.NORMAL.toString()).and();
        });

        http.formLogin().loginPage("/login")
                .successHandler(loginSuccessHandler)
                .permitAll().and();




        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                .permitAll()
                .and();

        http.authorizeHttpRequests()
                .requestMatchers("/api/v1/**")
                .authenticated().and();

        http.authorizeHttpRequests()
                .requestMatchers("/**")
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
