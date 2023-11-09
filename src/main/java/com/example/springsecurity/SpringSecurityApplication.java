package com.example.springsecurity;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@RequiredArgsConstructor

public class SpringSecurityApplication implements CommandLineRunner {
    private final RedisTemplate<String, String> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class);
        System.out.println("Spring application is running at : " + new Date());
    }

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().set("key1","khue1234");
        System.out.println("commandline in main running ...");
    }
}
