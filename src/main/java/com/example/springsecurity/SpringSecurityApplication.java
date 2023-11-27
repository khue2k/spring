package com.example.springsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor

public class SpringSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class);
        System.out.println("-------------Spring application is running at : " + new Date() + "--------------");
    }
}
