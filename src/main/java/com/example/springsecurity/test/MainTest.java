package com.example.springsecurity.test;


public class MainTest {

    public static String change(String objectName, int index, String newPath) {
        String[] paths = objectName.split("/");
        paths[index] = newPath;
        return String.join("/", paths);
    }


    public static void main(String[] args) throws Exception {
        System.out.println(Long.valueOf(null));
    }


}
