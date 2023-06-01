package com.beetech.mvcspringboot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterUtils {
    private DateTimeFormatterUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
