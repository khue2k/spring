package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustomRepository {
    Optional<User> findByEmail(String email);

    @Query(value = "update User u set u.numberAttempt= ?1 where u.email= ?2")
    @Modifying
    void updateFailedAttempts(int failedAttempts, String email);

    boolean existsByEmail(String email);
}
