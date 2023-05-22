package org.example.java17;

public sealed class Animal permits Cat, Dog{
    public void sound() {
        System.out.println("Test sound");
    }
}
