package com.example.IdentityService.reposiroty;

import com.example.IdentityService.entities.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
    boolean existsByToken(String token);

    Confirmation findByToken(String token);
}
