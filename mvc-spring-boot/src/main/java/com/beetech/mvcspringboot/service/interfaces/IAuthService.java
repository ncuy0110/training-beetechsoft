package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.dto.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.dto.LoginRequest;

public interface IAuthService {
    AuthenticationResponse login(LoginRequest request);
}
