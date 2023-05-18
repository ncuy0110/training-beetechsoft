package org.example;

public class NonThreadSafeVariable {
    private Long count = 0L;
    public Long getCount() {
        return count;
    }

    public void plus() {
        count++;
    }
    public void minus() {
        count--;
    }

    public static void main(String[] args) {
        NonThreadSafeVariable ntsv = new NonThreadSafeVariable();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ntsv.plus();
                System.out.println(ntsv.getCount());
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ntsv.minus();
                System.out.println(ntsv.getCount());
            }
        });

        t1.start();
        t2.start();
    }
}
