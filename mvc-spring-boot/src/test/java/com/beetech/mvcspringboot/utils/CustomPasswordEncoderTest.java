package com.beetech.mvcspringboot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomPasswordEncoderTest {
    @Test
    public void encodeAndComparePassword() {
        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        String password = "testP@ssword";
        String encodedPassword = customPasswordEncoder.encode(password);
        Assertions.assertTrue(customPasswordEncoder.matches(password, encodedPassword));
    }
}