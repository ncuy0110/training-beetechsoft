package com.beetech.mvcspringboot.controller.publics.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * The type Register dto.
 */
@ToString
public class RegisterDto extends LoginDto{
    @NonNull
    @Getter
    @Setter
    private String repeatPassword;

    /**
     * Instantiates a new Register dto.
     *
     * @param username       the username
     * @param password       the password
     * @param repeatPassword the repeat password
     */
    public RegisterDto(String username, String password, @NonNull String repeatPassword) {
        super(username, password);
        this.repeatPassword = repeatPassword;
    }
}
