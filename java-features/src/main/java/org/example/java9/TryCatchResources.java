package org.example.java9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class TryCatchResources {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new StringReader("Hello world!\nHello world2!"))) {
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error happened");
        }
    }
}
