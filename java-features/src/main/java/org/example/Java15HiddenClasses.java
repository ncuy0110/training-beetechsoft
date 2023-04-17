package org.example;

import java.lang.reflect.InvocationTargetException;

public class Java15HiddenClasses {
    public static void main(String[] args) {
        Object obj = new Object() {
            public void test() {
                System.out.println("Hello world");
            }
        };
        try {
            obj.getClass().getMethod("test").invoke(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
