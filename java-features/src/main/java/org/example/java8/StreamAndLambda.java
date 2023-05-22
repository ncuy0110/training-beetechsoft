package org.example.java8;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamAndLambda {
    public static void sumRandomIntegerList() {
        // random 100 so trong doan 1-100
        List<Integer> numbers = new Random().ints(100, 1, 100)
                .boxed()
                .collect(Collectors.toList());
        int sum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
    }

    public static void main(String[] args) {
        sumRandomIntegerList();
    }
}
