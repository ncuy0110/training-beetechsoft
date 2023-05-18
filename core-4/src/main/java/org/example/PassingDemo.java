package org.example;

public class PassingDemo {
    public static void passByValue(String s) {
        s += " world!";
    }
    public static void passByReference(StringBuilder sb) {
        sb.append(" world!");
    }

    public static void main(String[] args) {
        String s = "Hello";
        passByValue(s);
        System.out.println(s);

        StringBuilder sb = new StringBuilder("Hello");
        passByReference(sb);
        System.out.println(sb);
    }
}
