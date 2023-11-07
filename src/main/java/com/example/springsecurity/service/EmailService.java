package com.example.springsecurity.service;


import com.example.springsecurity.dto.EmailDetail;

public interface EmailService {
    void sendEmailWithSimpleText(String name, String to, String token);


    void sendEmailWithAttachment(String name, String to, String token);

    void sendHtmlEmail(String name, String to, String token);

    void sendMimeMessageWithAttachment(String name, String to, String token);
}
