package org.example;

import java.util.*;

public class CollectionModel {
    private static final int limit = 1000000;
    private static Random rd = null;

    private static Integer getRandom() {
        if (rd == null) {
            rd = new Random();
        }
        return rd.nextInt(limit);
    }

    public static Long countList(List<Integer> list) {
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            list.add(getRandom());
        }
        list.remove(getRandom());
        list.contains(getRandom());
        Long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[limit];
        for (int i = 0; i < limit; i++) {
            arr[i] = getRandom();
        }

        List<Integer> arrayList = new ArrayList<>(Arrays.asList(arr));
        System.out.println("Array List: " + countList(arrayList));
//        arrayList.forEach(System.out::println);

        List<Integer> linkedList = new LinkedList<>(Arrays.asList(arr));
        System.out.println("Linked List: " + countList(linkedList));
//        linkedList.forEach(System.out::println);

    }
}
