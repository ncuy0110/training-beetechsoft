package org.example;

import javax.script.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Java8 {
    interface Demo {
        default void test() {
            System.out.println("Hello world!");
        }
    }

    class Demo2 implements Demo {

    }

    public void testInterface() {
        Demo2 d = new Demo2();
        d.test();
    }

    public static void sumRandomIntegerList() {
        // random 100 so trong doan 1-100
        List<Integer> numbers = new Random().ints(100, 1, 100)
                // chuyen tu dang nguyen thuy sang Integer
                .boxed()
                // chuyen doi danh sach co duoc thanh list
                .collect(Collectors.toList());
        int sum = numbers.stream()
                // apply filter cho từng số trong list, nếu thỏa mãn thì giữ lại
                .filter(number -> number % 2 == 0)
                // map danh sách có được thành dạng int
                .mapToInt(Integer::intValue)
                // tính tổng của các số đó
                .sum();
        System.out.println(sum);
    }

    public static void showCurrentDatetime() {
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public static void main(String[] args) throws ScriptException {
        showCurrentDatetime();

        new Java8().testInterface();

        // tạo list gồm 3 phần tử String
        List<String> names = Arrays.asList("John", "Mary", "Bob");
        // sắp xếp dựa trên hàm compareToIgnoreCase của lớp String
        names.sort(String::compareToIgnoreCase);
        // in lần lượt các phần tử trong list
        names.forEach(System.out::println);

        Optional<String> optionalName = Optional.ofNullable(null);
        if (optionalName.isPresent()) {
            System.out.println(optionalName.get());
        } else {
            System.out.println("Unknown");
        }
    }
}
