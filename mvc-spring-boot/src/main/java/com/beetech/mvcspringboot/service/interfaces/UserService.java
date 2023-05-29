package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.dto.RegisterDto;
import com.beetech.mvcspringboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends UserDetailsService {
    User register(RegisterDto registerDto);

    @Override
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void deleteUserById(Long userId);
}