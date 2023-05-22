package org.example.java14;

public class YieldKeyword {
    public static void main(String[] args) {
        int month = 12;
        int quarter = switch (month) {
            case 1, 2, 3 -> 1;
            case 4, 5, 6 -> 2;
            case 7, 8, 9 -> 3;
            case 10, 11, 12 -> 4;
            default -> 0;
        };
        System.out.println(quarter);
    }
}
