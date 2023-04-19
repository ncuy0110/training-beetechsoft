package org.example.java9;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.StringReader;

public class TryCatchResources {
    public static void main(String[] args) {
        final BufferedReader br = new BufferedReader(new StringReader("Hello world!"));
        try (BufferedReader reader = br) {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error happened");
        }
    }
}
