package com.example.springsecurity.service;


import com.example.springsecurity.dto.EmailDetail;

import javax.mail.MessagingException;

public interface EmailService {
    void sendEmailWithSimpleText(String name, String to, String token);


    void sendEmailWithAttachment(String name, String to, String token);

    void sendHtmlEmail(String name, String to, String token) throws MessagingException;

    void sendMimeMessageWithAttachment(String name, String to, String token);
}
