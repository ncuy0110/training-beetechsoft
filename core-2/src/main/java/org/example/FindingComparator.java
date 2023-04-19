package org.example;

import java.util.*;

public class FindingComparator {
    public static final int limit = 1000000;

    private static Random rd = null;

    private static Integer getRandom() {
        if (rd == null) {
            rd = new Random();
        }
        return rd.nextInt(limit);
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[limit];
        for (int i = 0; i < limit; i++) {
            arr[i] = getRandom();
        }

        ArrayList<Integer> arrayList = AddingComparator.genArrayList(arr);
        LinkedList<Integer> linkedList = AddingComparator.genLinkedList(arr);
        HashSet<Integer> hashSet = AddingComparator.genHashSet(arr);
        PriorityQueue<Integer> priorityQueue = AddingComparator.genPriorityQueue(arr);
        HashMap<Integer, Integer> hashMap = AddingComparator.genHashMap(arr);

        // Array List
        long start = System.nanoTime();
        arrayList.contains(getRandom());
        arrayList.contains(getRandom());
        arrayList.contains(getRandom());
        long end = System.nanoTime();
        System.out.println("Array List: " + (end - start));

        // Linked List
        start = System.nanoTime();
        linkedList.contains(getRandom());
        linkedList.contains(getRandom());
        linkedList.contains(getRandom());
        end = System.nanoTime();
        System.out.println("Linked List: " + (end - start));

        // HashSet
        start = System.nanoTime();
        hashSet.contains(getRandom());
        hashSet.contains(getRandom());
        hashSet.contains(getRandom());
        end = System.nanoTime();
        System.out.println("Hash set: " + (end - start));

        // Priority queue
        start = System.nanoTime();
        priorityQueue.contains(getRandom());
        priorityQueue.contains(getRandom());
        priorityQueue.contains(getRandom());
        end = System.nanoTime();
        System.out.println("Priority Queue: " + (end - start));

        // Hash map
        start = System.nanoTime();
        hashMap.containsKey(getRandom());
        hashMap.containsKey(getRandom());
        hashMap.containsKey(getRandom());
        end = System.nanoTime();
        System.out.println("Hash map: " + (end - start));
    }
}
