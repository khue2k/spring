package com.example.springsecurity.service.impl;

import com.example.springsecurity.service.EmailService;
import com.example.springsecurity.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender sender;

    @Override
    public void sendEmailWithSimpleText(String name, String to, String token) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("New user account verification ");
            message.setText(MailUtils.getEmailMessage(name, "127.0.0.1:8080", token));
            sender.send(message);

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void sendEmailWithAttachment(String name, String to, String token) {

    }

    @Override
    public void sendHtmlEmail(String name, String to, String token) {

    }

    @Override
    public void sendMimeMessageWithAttachment(String name, String to, String token) {

    }
}
