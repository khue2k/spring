package com.example.IdentityService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import java.util.Date;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringSecurityApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class);
        System.out.println("-------------Spring application is running at : " + new Date() + "--------------");
    }

    @Override
    public void run(ApplicationArguments args) {
    }
}
