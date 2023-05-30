package com.beetech.mvcspringboot.controller.publics.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


/**
 * The type Register dto.
 */
@ToString
@RequiredArgsConstructor
@Data
public class RegisterDto {
    /**
     * username String
     */
    @NonNull
    @NotBlank
    @Size(min = 5, message = "{validation.name.size.too_short}")
    @Size(max = 50, message = "{validation.name.size.too_long}")
    private String username;

    /**
     * password String
     */
    @NonNull
    @NotBlank
    @Size(min = 5, message = "{validation.name.size.too_short}")
    @Size(max = 50, message = "{validation.name.size.too_long}")
    private String password;

    /**
     * repeatPassword String
     */
    @NonNull
    @NotBlank
    @Size(min = 5, message = "{validation.name.size.too_short}")
    @Size(max = 50, message = "{validation.name.size.too_long}")
    private String repeatPassword;
}
