package com.beetech.mvcspringboot.dto;

/**
 * The type Register dto.
 */
public class RegisterDto extends LoginDto{

    /**
     * Instantiates a new Register dto.
     *
     * @param username the username
     * @param password the password
     */
    public RegisterDto(String username, String password) {
        super(username, password);
    }
}
