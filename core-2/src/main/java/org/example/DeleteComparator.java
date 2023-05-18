package org.example;

import java.util.*;

public class DeleteComparator {
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
        arrayList.removeIf(e -> e == getRandom());
        long end = System.nanoTime();
        System.out.println("Array List: " + (end - start));

        // Linked List
        start = System.nanoTime();
        linkedList.removeIf(e -> e == getRandom());
        end = System.nanoTime();
        System.out.println("Linked List: " + (end - start));

        // HashSet
        start = System.nanoTime();
        hashSet.remove(getRandom());
        end = System.nanoTime();
        System.out.println("Hash set: " + (end - start));

        // Priority queue
        start = System.nanoTime();
        priorityQueue.removeIf(e -> e == getRandom());
        end = System.nanoTime();
        System.out.println("Priority Queue: " + (end - start));

        // Hash map
        start = System.nanoTime();
        hashMap.remove(getRandom());
        end = System.nanoTime();
        System.out.println("Hash map: " + (end - start));
    }
}
