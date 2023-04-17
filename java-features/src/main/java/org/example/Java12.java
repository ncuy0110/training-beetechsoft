package org.example;

public class Java12 {
    public static void testSwitchCaseExpression() {
        int point = 8;
        String rs = switch (point) {
            case 1, 2, 3, 4 -> "Yeu";
            case 5, 6 -> "Trung binh";
            case 7, 8 -> "Kha";
            case 9, 10 -> "Gioi";
            default -> "";
        };
        System.out.println(rs);
    }

    public static void main(String[] args) {
        testSwitchCaseExpression();
    }
}
