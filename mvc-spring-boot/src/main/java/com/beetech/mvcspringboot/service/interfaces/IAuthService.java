package com.beetech.mvcspringboot.service.interfaces;

import com.beetech.mvcspringboot.controller.AuthenticationResponse;
import com.beetech.mvcspringboot.controller.LoginRequest;

public interface IAuthService {
    AuthenticationResponse login(LoginRequest request);
}
