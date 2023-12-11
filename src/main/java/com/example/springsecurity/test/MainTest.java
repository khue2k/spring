package com.example.springsecurity.test;

public class MainTest {
    int solve(int a[], int k) {

        int result = 0;
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += a[i];
        }
        result = windowSum;

        for (int i = k; i < a.length; ++i) {
            windowSum = windowSum + a[i] - a[i - k];
            result = Math.max(windowSum, result);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MainTest().solve(new int[]{100, 2, 3, 4, 5, 6}, 3));
    }
}