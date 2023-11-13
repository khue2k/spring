package com.example.springsecurity.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {
    public static List<String> convert(List<String> list) {
        return list.stream().filter(s -> s != null).map(s -> s.substring(0, s.length() - 2)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(convert(Arrays.asList("20230901", "20231103")));
    }
}
