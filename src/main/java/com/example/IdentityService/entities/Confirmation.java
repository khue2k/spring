package com.example.IdentityService.entities;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Table(name = "confirmation")
@Entity
@Data
public class Confirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date createDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public Confirmation(User user) {
        this.user = user;
        this.createDate = new Date();
        this.token = UUID.randomUUID().toString();
    }

    public Confirmation() {

    }
}
