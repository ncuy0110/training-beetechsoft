package com.beetech.mvcspringboot.controller.publics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

/**
 * The type Login dto.
 */
@Data
@AllArgsConstructor
@ToString
public class LoginDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
