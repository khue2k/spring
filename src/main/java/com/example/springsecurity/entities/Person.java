package com.example.springsecurity.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.annotation.Transient;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@RedisHash(value = "ptit")
@Data
public class Person {

    @Id
    private String id;
    @NotNull
    @Email
    @EqualsAndHashCode.Include
    @ToString.Include
    @Indexed
    private String email;

    private String address;
    @NotNull
    private String password;
    @Transient
    private String confirmPassword;
    @Reference
    private Set<Phone> phones;
}
