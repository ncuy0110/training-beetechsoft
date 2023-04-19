package org.example.java14;

public class SwitchExpression {
    public static void main(String[] args) {
        int month = 12;
        int quarter = switch (month) {
            case 1, 2, 3 -> {
                System.out.println(month);
                yield 1;
            }
            case 4, 5, 6 -> {
                System.out.println(month);
                yield 2;
            }
            case 7, 8, 9 -> {
                System.out.println(month);
                yield 3;
            }
            case 10, 11, 12 -> {
                System.out.println(month);
                yield 4;
            }
            default -> 0;
        };
        System.out.println(quarter);
    }
}
