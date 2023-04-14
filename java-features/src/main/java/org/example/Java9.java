package org.example;

public class Java9 {
    interface IDemo {
        private String getName() {
            return "Uy";
        }

        default void test() {
            System.out.println(getName());
        }
    }

    class Demo implements IDemo {

    }

    public void testInterface() {
        Demo d = new Demo();
        d.test();
    }

    public static void main(String[] args) {
        new Java9().testInterface();

    }
}
