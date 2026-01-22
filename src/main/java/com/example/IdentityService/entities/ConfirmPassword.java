package com.example.IdentityService.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "confirm_password")
public class ConfirmPassword extends BaseEntity {
    @Column(name = "token", unique = true)
    private String token;
    @OneToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ConfirmPassword(User user) {
        this.user = user;
        this.token= UUID.randomUUID().toString();
    }

    public ConfirmPassword() {

    }
}
