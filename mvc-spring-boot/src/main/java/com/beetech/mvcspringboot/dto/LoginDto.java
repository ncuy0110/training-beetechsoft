package com.beetech.mvcspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * The type Login dto.
 */
@Data
@AllArgsConstructor
@ToString
public class LoginDto {
    private String username;
    private String password;
}
