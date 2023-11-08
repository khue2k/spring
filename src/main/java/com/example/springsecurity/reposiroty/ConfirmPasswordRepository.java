package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.ConfirmPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmPasswordRepository extends JpaRepository<ConfirmPassword, Long> {
    boolean existsByToken(String token);

    Optional<ConfirmPassword> findByToken(String token);
}
