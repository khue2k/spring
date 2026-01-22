package com.example.IdentityService.reposiroty;

import com.example.IdentityService.entities.TokenLogout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenLogoutRepository extends JpaRepository<TokenLogout, Long> {
    boolean existsByValue(String token);
}
