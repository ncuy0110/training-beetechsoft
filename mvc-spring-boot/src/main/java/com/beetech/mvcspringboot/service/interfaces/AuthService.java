package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.publics.dto.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.publics.dto.LoginDto;

public interface AuthService {
    AuthenticationResponse login(LoginDto request);
}
