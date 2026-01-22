package com.example.IdentityService.service;


import javax.mail.MessagingException;

public interface EmailService {
    void sendEmailConfirmNewUser(String name, String to, String token);

    void sendEmailForgotPassword(String name, String to, String token);


    void sendEmailWithAttachment(String name, String to, String token);

    void sendHtmlEmail(String name, String to, String token) throws MessagingException;

    void sendMimeMessageWithAttachment(String name, String to, String token);
}
