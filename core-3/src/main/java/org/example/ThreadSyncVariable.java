package org.example;

public class ThreadSyncVariable extends Thread {
    private static boolean blocked = false;
    private final String name;

    public ThreadSyncVariable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            if (!blocked) {
                blocked = true;
                for (int i = 0; i < 10; i++) {
                    System.out.println(name + ":" + i);
                }
                blocked = false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        ThreadSyncVariable tsv1 = new ThreadSyncVariable("1");
        ThreadSyncVariable tsv2 = new ThreadSyncVariable("2");

        tsv1.start();
        tsv2.start();
    }
}
