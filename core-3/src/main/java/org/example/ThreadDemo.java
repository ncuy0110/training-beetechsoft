package org.example;

public class ThreadDemo {
    private static final Object lock = new Object();

    public static void test0() {
        for (int i = 0; i < 10; i++) {
            System.out.println("0: " + i);
        }
    }

    public static void test1() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.println("1: " + i);
            }
        }
    }

    public static void test2() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.println("2: " + i);
            }
        }
    }

    public static synchronized void test3() {
        for (int i = 0; i < 10; i++) {
            System.out.println("3: " + i);
        }
    }

    public static synchronized void test4() {
        for (int i = 0; i < 10; i++) {
            System.out.println("4: " + i);
        }
    }

    public static void main(String[] args) {
        Thread t0 = new Thread(ThreadDemo::test0);
        t0.start();

        Thread t1 = new Thread(ThreadDemo::test1);
        Thread t2 = new Thread(ThreadDemo::test2);

        t1.start();
        t2.start();

        Thread t3 = new Thread(ThreadDemo::test3);
        Thread t4 = new Thread(ThreadDemo::test4);

        t3.start();
        t4.start();
    }
}
