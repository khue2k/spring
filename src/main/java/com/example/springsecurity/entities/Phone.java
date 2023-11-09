package com.example.springsecurity.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash(timeToLive = 30 * 60 * 1000,value = "ptit")
public class Phone {
    @Id
    private String id;
    private String number;
}
