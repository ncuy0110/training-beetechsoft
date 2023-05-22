package org.example;

public class Java16 {
    public record VehicleRecord(String code, String engineType) {}
    public static void main(String[] args) {
        VehicleRecord vr = new VehicleRecord("123", "test");
        System.out.println(vr);
    }
}
