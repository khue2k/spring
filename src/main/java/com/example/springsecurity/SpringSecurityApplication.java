package com.example.springsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringSecurityApplication implements ApplicationRunner {
    @Value("${huhu}")
    private String value;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class);
        System.out.println("-------------Spring application is running at : " + new Date() + "--------------");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("this is value: " + value);
    }
}
