package com.beetech.mvcspringboot.controller.publics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.Size;


/**
 * The type Login dto.
 */
@Data
@AllArgsConstructor
@ToString
public class LoginDto {
    @NonNull
    @Size(min = 5, max = 50)
    private String username;
    @NonNull
    @Size(min = 5, max = 50)
    private String password;
}
