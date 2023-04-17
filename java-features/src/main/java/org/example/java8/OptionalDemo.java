package org.example.java8;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> optionalName = Optional.ofNullable(null);
        if (optionalName.isPresent()) {
            System.out.println(optionalName.get());
        } else {
            System.out.println("Unknown");
        }
    }
}
