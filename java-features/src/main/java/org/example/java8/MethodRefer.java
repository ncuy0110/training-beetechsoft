package org.example.java8;

import javax.script.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MethodRefer {
    public static void main(String[] args) throws ScriptException {
        // tạo list gồm 3 phần tử String
        List<String> names = Arrays.asList("John", "Mary", "Bob");
        // sắp xếp dựa trên hàm compareToIgnoreCase của lớp String
        names.sort(String::compareToIgnoreCase);
        // in lần lượt các phần tử trong list
        names.forEach(System.out::println);
    }
}
