package org.example;

public class Java13 {
    public static void testTextBlock() {
        String a = """
                Hello
                My name is Uy.
                I'm 22.
                """;
        System.out.println(a);
    }

    public static void main(String[] args) {
        testTextBlock();
    }
}
