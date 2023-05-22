package org.example;

import org.ehcache.sizeof.SizeOf;

public class StringAndStringBuilderComparing {

    public static void main(String[] args) {
        SizeOf sizeOf = SizeOf.newInstance();
        long startTime = System.nanoTime();
        String s = "";
        for (int i = 0; i < 100; i++) {
            s += ("Hello " + i + " world\n");
        }
        long endTime = System.nanoTime();
        System.out.println(sizeOf.deepSizeOf(s) + " " + (endTime - startTime));

        startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append("Hello ");
            sb.append(i);
            sb.append(" world\n");
        }
        endTime = System.nanoTime();
        System.out.println(sizeOf.deepSizeOf(sb) + " " + (endTime - startTime));
    }
}
