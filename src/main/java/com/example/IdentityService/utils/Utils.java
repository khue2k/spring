package com.example.IdentityService.utils;

import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;


public class Utils {
    public static Date pushMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(pushMinute(new Date(), 30));
    }

    public static String getToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
