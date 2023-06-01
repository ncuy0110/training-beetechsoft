package com.beetech.mvcspringboot.configuration;

import com.beetech.mvcspringboot.constants.RoleEnum;
import com.beetech.mvcspringboot.security.AuthenticationSuccessHandler;
import com.beetech.mvcspringboot.security.JwtAuthenticationFilter;
import com.beetech.mvcspringboot.service.implement.UserServiceImpl;
import com.beetech.mvcspringboot.utils.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SecurityConfig {
    /**
     * inject user service bean
     */
    private final UserServiceImpl userService;
    /**
     * inject custom password encoder bean
     */
    private final CustomPasswordEncoder passwordEncoder;
    /**
     * inject jwt authentication filter bean
     */
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    /**
     * inject authentication success handler
     */
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * Auth manager authentication manager.
     *
     * @param http               the http
     * @param userDetailsService the user details service
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(this.passwordEncoder).and().build();
    }

    /**
     * Authentication provider dao authentication provider.
     *
     * @return the dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(this.userService);
        auth.setPasswordEncoder(this.passwordEncoder);
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
        http.cors().disable();

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/v1/**"));

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
                .successHandler(this.authenticationSuccessHandler)
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
                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
