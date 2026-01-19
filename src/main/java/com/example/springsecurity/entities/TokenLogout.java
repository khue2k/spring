package com.example.springsecurity.entities;

import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "token_logout")
@Entity
@Setter
public class TokenLogout extends BaseEntity {
    @Column(name = "value")
    private String value;
}
