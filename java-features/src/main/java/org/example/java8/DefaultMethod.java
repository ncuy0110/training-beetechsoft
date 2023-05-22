package org.example.java8;

public class DefaultMethod {
    interface Demo {
        default void test() {
            System.out.println("Hello world!");
        }
    }

    class Demo2 implements Demo {

    }

    public void testInterface() {
        Demo2 d = new Demo2();
        d.test();
    }

    public static void main(String[] args) {
        new DefaultMethod().testInterface();
    }
}
