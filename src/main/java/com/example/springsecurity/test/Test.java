package com.example.springsecurity.test;

import java.util.Collections;

class A {
    void m1() throws ArrayIndexOutOfBoundsException {
        System.out.println("AAA");
    }
}

class B extends A {
    void m1() throws IndexOutOfBoundsException {
        System.out.println("BBB");
    }
}

public class Test {
    public static void main(String[] args) {
        int result, x;
        x = 1;
        result = 0;
        while (x <= 10) {
            if (x % 2 == 0)
                result += x;
            x++;
        }
        System.out.println(result);

    }
}
