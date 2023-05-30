package com.beetech.mvcspringboot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomPasswordEncoderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomPasswordEncoderTest.class);
    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Initialize for Password Encoder");
        }

        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();
        when(passwordEncoder.encode(any(CharSequence.class)))
                .then(new Answer<String>() {
                    @Override
                    public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                        CharSequence plainTextPassword = invocationOnMock.getArgument(0);
                        return customPasswordEncoder.encode(plainTextPassword);
                    }
                });

        when(passwordEncoder.matches(any(CharSequence.class), any(String.class))).then(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
                CharSequence rawPassword = invocationOnMock.getArgument(0);
                String encodedPassword = invocationOnMock.getArgument(1);
                return customPasswordEncoder.matches(rawPassword, encodedPassword);
            }
        });
    }

    @Test
    void encodeAndComparePassword() {
        String password = "testP@ssword";
        String encodedPassword = passwordEncoder.encode(password);
        Assertions.assertTrue(passwordEncoder.matches(password, encodedPassword));
    }
}