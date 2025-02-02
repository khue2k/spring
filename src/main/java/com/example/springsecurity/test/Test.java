package com.example.springsecurity.test;

import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        TreeSet<Integer> s = new TreeSet<>();
        TreeSet<Integer> subSet;
        for (int i = 606; i < 613; i++) {
            if (i % 2 == 0) {
                s.add(i);
            }
        }
        subSet = (TreeSet<Integer>) s.subSet(608, true, 611, false  );
        s.add(609);

        System.out.println(s + " " + subSet);
    }
}