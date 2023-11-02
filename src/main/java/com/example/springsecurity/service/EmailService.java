package com.example.springsecurity.service;


import com.example.springsecurity.dto.EmailDetail;

public interface EmailService {
    String sendEmailWithSimpleText(EmailDetail emailDetail);

    String sendEmailWithAttachment(EmailDetail emailDetail);
}
