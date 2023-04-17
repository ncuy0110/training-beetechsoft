package org.example.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeAPI {
    public static void showCurrentDatetime() {
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public static void main(String[] args) {
        showCurrentDatetime();
    }
}
