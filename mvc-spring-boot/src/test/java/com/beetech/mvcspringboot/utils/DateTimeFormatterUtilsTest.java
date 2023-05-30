package com.beetech.mvcspringboot.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeFormatterUtilsTest {
    @Test
    void formatDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 5, 19, 14, 10, 0, 0);
        Assertions.assertEquals("2023-05-19 14:10:00", DateTimeFormatterUtils.formatLocalDateTime(localDateTime));
    }

}