package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.dto.RegisterDto;
import com.beetech.mvcspringboot.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService extends UserDetailsService {
    User save(RegisterDto registerDto);

    @Override
    @Transactional
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}