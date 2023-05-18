package org.example;

public class ThreadSafeVariable {
    private Long count = 0L;
    private Object locked = new Object();

    public Long getCount() {
        return count;
    }

    public void plus() {
        synchronized (locked) {
            count++;
        }
    }

    public void minus() {
        synchronized (locked) {
            count--;
        }
    }

    public static void main(String[] args) {
        ThreadSafeVariable tsv = new ThreadSafeVariable();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tsv.plus();
                System.out.println(tsv.getCount());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                tsv.minus();
                System.out.println(tsv.getCount());
            }
        });

        t1.start();
        t2.start();
    }
}
