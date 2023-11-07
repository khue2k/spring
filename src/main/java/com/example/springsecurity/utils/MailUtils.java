package com.example.springsecurity.utils;

public class MailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + "\n\nYour new account has been created. " +
                "Please click the link below to verify your account.\n\n "
                + "<a href='" + getVerifyUrl(host, token) + "'>Here</a>" +
                "\n\n\n Thank you .";

    }

    private static String getVerifyUrl(String host, String token) {
        return host + "/api/user?token=?" + token;
    }
}
