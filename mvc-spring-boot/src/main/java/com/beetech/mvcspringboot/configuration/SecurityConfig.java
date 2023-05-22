package com.beetech.mvcspringboot.configuration;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.security.AuthenticationSuccessHandler;
import com.beetech.mvcspringboot.security.JwtAuthenticationFilter;
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
import org.springframework.security.web.savedrequest.CookieRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The type Security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserServiceImpl userService;
    private final CustomPasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * Instantiates a new Security config.
     *
     * @param userService                  the user service
     * @param passwordEncoder              the password encoder
     * @param jwtAuthenticationFilter      jwt filter
     * @param authenticationSuccessHandler handler on login success
     */
    public SecurityConfig(
            UserServiceImpl userService,
            CustomPasswordEncoder passwordEncoder,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
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
//        http.cors().and().csrf().disable();
        http.cors().disable();

        http.csrf(httpSecurityCsrfConfigurer -> {
            httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/v1/**");
        });

        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/admin/**")
                    .hasAuthority(RoleEnum.ADMIN.toString()).and();

            request.requestMatchers("/api/v1/cart/**")
                    .authenticated().and();

            request.requestMatchers("/api/v1/cart/**")
                    .authenticated().and();

            request.requestMatchers("/pay")
                    .authenticated().and();

            request.requestMatchers("/order/**")
                    .authenticated().and();
        });

        http.formLogin().loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .permitAll()
                .and()
                .requestCache()
                .requestCache(new CookieRequestCache())
                .and();

        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("accessToken"));


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
