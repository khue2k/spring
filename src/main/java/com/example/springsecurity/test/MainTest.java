package com.example.springsecurity.test;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MainTest {
    int solve(int n) {
        int result = 0;
        while (n > 0) {
            result = result * 10 + n % 10;
            n = n / 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MainTest().solve(12345));
    }
}