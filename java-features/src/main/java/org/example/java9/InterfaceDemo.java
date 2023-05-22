package org.example.java9;

public class InterfaceDemo {
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
        new InterfaceDemo().testInterface();

    }
}
