package com.example.IdentityService.reposiroty;

import com.example.IdentityService.entities.Role;
import com.example.IdentityService.utils.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}
