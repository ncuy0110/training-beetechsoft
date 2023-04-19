package org.example.java9;

import java.util.ArrayList;
import java.util.List;

public class DiamondSyntax {
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<String>();
        list1.add("asd");
        list1.add("asd");
        list1.add("asd");
        list1.forEach(System.out::println);

        List<String> list2 = new ArrayList<>();
        list2.add("asffa");
        list2.add("asffa");
        list2.add("asffa");
        list2.forEach(System.out::println);
    }
}
