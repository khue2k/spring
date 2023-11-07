package com.example.springsecurity.service.impl;

import com.example.springsecurity.service.EmailService;
import com.example.springsecurity.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
            message.setText("Hihihi");
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
        try {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("New user account verification ");
            String emailContent = MailUtils.getEmailMessage(name, "localhost:8080", token);
            helper.setText(emailContent, true);

            sender.send(message);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
        }

    }

    @Override
    public void sendMimeMessageWithAttachment(String name, String to, String token) {

    }
}
