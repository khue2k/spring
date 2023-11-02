package com.example.springsecurity.service.impl;

import com.example.springsecurity.dto.EmailDetail;
import com.example.springsecurity.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${sender}")
    private String sender;
    private final JavaMailSender javaMailSender;

    @Override
    public String sendEmailWithSimpleText(EmailDetail detail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(detail.getRecipient());
            message.setSubject(detail.getSubject());
            message.setText(detail.getSubject());
            javaMailSender.send(message);
            return "Mail send successfully ...";
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "Error while sending email !";
        }
    }

    @Override
    public String sendEmailWithAttachment(EmailDetail emailDetail) {
        return null;
    }
}
