package org.example;

public class Java15SealedClasses {
    public sealed class Animal permits Cat, Dog, Chicken {
        public void playSound() {
            System.out.println("hmm");
        }
    }

    public final class Cat extends Animal {
        @Override
        public void playSound() {
            System.out.println("meow meow");
        }
    }

    public final class Dog extends Animal {
    }

    public final class Chicken extends Animal {

    }

}
