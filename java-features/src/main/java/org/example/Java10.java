package org.example;

public class Java10 {
    public static void main(String[] args) {
        // tự động chỉ định kiểu dữ liệu dựa trên giá trị
        var s = new String("Hello world");
        System.out.println(s.getClass());
    }
}
