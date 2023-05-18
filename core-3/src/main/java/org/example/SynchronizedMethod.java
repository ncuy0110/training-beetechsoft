package org.example;

public class SynchronizedMethod {
    private Long count = 0L;

    public synchronized Long getCount() {
        return count;
    }

    public synchronized void plus() {
        count++;
    }

    public synchronized void minus() {
        count--;
    }

    public static void main(String[] args) {
        SynchronizedMethod sm = new SynchronizedMethod();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sm.plus();
                System.out.println(sm.getCount());
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sm.minus();
                System.out.println(sm.getCount());
            }
        }).start();
    }
}
