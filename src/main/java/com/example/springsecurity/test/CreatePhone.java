package com.example.springsecurity.test;

import com.example.springsecurity.entities.Phone;
import com.example.springsecurity.reposiroty.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class CreatePhone implements CommandLineRunner {
    private final PhoneRepository phoneRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("create phone ");
        if (phoneRepository.count() == 0) {
            Phone phone = new Phone();
            phone.setNumber("0916933280");
            phoneRepository.save(phone);
        }
    }
}
