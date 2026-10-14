package com.example.IdentityService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class IdentityServiceApp implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(IdentityServiceApp.class);
    }

    @Override
    public void run(ApplicationArguments args) {
    }
}
