package org.example;


import java.util.*;

public class AddingComparator {
    public static final int limit = 1000000;
    private static Random rd = null;

    private static Integer getRandom() {
        if (rd == null) {
            rd = new Random();
        }
        return rd.nextInt(limit);
    }

    public static ArrayList<Integer> genArrayList(Integer[] arr) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            arrayList.add(arr[i]);
        }
        return arrayList;
    }

    public static LinkedList<Integer> genLinkedList(Integer[] arr) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < limit; i++) {
            linkedList.add(arr[i]);
        }
        return linkedList;
    }

    public static HashSet<Integer> genHashSet(Integer[] arr) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < limit; i++) {
            hashSet.add(arr[i]);
        }
        return hashSet;
    }

    public static PriorityQueue<Integer> genPriorityQueue(Integer[] arr) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < limit; i++) {
            priorityQueue.add(arr[i]);
        }
        return priorityQueue;
    }

    public static HashMap<Integer, Integer> genHashMap(Integer[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < limit; i++) {
            hashMap.put(arr[i], arr[i]);
        }
        return hashMap;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[limit];
        for (int i = 0; i < limit; i++) {
            arr[i] = getRandom();
        }

        // array list
        long start = System.nanoTime();
        genArrayList(arr);
        long end = System.nanoTime();
        System.out.println("Array list: " + (end - start));

        // linked list
        start = System.nanoTime();
        genLinkedList(arr);
        end = System.nanoTime();
        System.out.println("Linked list: " + (end - start));

        // HashSet
        start = System.nanoTime();
        genHashSet(arr);
        end = System.nanoTime();
        System.out.println("Hash set: " + (end - start));

        // Priority Queue
        start = System.nanoTime();
        genPriorityQueue(arr);
        end = System.nanoTime();
        System.out.println("Priority queue: " + (end - start));

        // hash map
        start = System.nanoTime();
        genHashMap(arr);
        end = System.nanoTime();
        System.out.println("Hash map: " + (end - start));
    }
}
