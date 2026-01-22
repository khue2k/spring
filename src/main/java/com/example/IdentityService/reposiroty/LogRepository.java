package com.example.IdentityService.reposiroty;

import com.example.IdentityService.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log,Long> {
}
