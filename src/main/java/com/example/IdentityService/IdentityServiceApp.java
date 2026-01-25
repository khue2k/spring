package com.example.IdentityService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class IdentityServiceApp implements ApplicationRunner {

    public static void main(String[] args) {
//        SpringApplication.run(IdentityServiceApp.class);
        ConfigurableApplicationContext context = SpringApplication.run(IdentityServiceApp.class, args);
        System.out.println("-------------Spring application is running at : " + new Date() + "--------------");


//        // In ra tất cả bean names
//        String[] beanNames = context.getBeanDefinitionNames();
//        Arrays.sort(beanNames); // sắp xếp để dễ đọc hơn
//
//        System.out.println("===== DANH SÁCH TẤT CẢ BEAN TRONG ỨNG DỤNG =====");
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
//        System.out.println("Tổng số bean: " + beanNames.length);
    }

    @Override
    public void run(ApplicationArguments args) {
    }
}
