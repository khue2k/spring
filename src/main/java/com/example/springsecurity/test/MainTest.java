package com.example.springsecurity.test;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MainTest {
    public static List<String> convert(List<String> list) {
        return list.stream().filter(s -> s != null).map(s -> s.substring(0, s.length() - 2)).collect(Collectors.toList());
    }
    public static String removeVietnameseAccentAndSpace(String text) {
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(text).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d").replaceAll("[ ]+","-");
    }
    public static void main(String[] args) {
        System.out.println(MainTest.removeVietnameseAccentAndSpace("Vũ Quang----      11111 1111   Đạt"));
    }
}
