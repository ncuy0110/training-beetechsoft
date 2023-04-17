package org.example;

public class Java14 {
    public record Person(String name, int age) {
    }

    public static void testRecord() {
        Person p = new Person("Uy", 22);
        System.out.println(p.name + " " + p.age);
    }

    public static void testInstanceOf() {
        var a = "hello";
        System.out.println((a instanceof String));
    }

    public static void main(String[] args) {
        testInstanceOf();
        testRecord();
    }
}
