package com.example.IdentityService.test.redis;

public class GenericExample {
    public static <T> void printValue(T t) {
        System.out.println(t);
    }

    public static <N extends Number> double addNumbers(N n1, N n2) {
        return n1.doubleValue() + n2.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(addNumbers(1, 2));
    }
}
