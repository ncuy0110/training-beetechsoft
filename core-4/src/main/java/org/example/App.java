package org.example;


import org.ehcache.sizeof.SizeOf;

public class App {
    public static void test1(int n) {
        n = 10;
    }

    public static void test2(StringBuilder sb) {
        sb.append("Hello");
    }

    public static void test3(int[] a) {
        a[0] += 10;
    }

    public static void main(String[] args) {
        SizeOf sizeOf = SizeOf.newInstance();

        long startTime = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 100; i++) {
            s += ("Hello " + i + " world\n");
        }
        long endTime = System.currentTimeMillis();
        System.out.println(sizeOf.deepSizeOf(s) + " " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("Hello " + i + " world\n");
        }
        endTime = System.currentTimeMillis();
        System.out.println(sizeOf.deepSizeOf(sb) + " " + (endTime - startTime));

        int[] a = new int[]{1, 2, 3};
        int n = 0;

        test1(n);
        System.out.println(n);

        test2(sb);
        System.out.println(sb);

        test3(a);
        System.out.println(a[0]);
    }

}
