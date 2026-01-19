package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.TokenLogout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenLogoutRepository extends JpaRepository<TokenLogout, Long> {
    boolean existsByValue(String token);
}
