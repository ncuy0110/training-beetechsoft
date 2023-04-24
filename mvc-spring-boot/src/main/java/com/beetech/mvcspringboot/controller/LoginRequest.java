package com.beetech.mvcspringboot.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
