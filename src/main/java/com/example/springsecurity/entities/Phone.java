package com.example.springsecurity.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Objects;

@Data
@RedisHash(timeToLive = 30 * 60 * 1000,value = "ptit")
@NoArgsConstructor
@AllArgsConstructor
public class Phone {
    @Id
    private String id;
    private String number;

    public Phone(String number) {
        this.number = number;
    }
}
