package com.example.springsecurity.utils;

public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + "\n\nYour new account has been created. " +
                "Please click the link below to verify your account.\n\n " +
                getVerifyUrl(host, token) +
                "\n\n\n Thank you .";

    }

    private static String getVerifyUrl(String host, String token) {
        return host + "/api/confirm?token=" + token;
    }
}