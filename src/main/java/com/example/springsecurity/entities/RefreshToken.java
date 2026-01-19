package com.example.springsecurity.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "refresh_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "token")
    private String token;
    @Column(name = "expire_date", nullable = false)
    private Instant expireDate;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userInfo;
    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

}
