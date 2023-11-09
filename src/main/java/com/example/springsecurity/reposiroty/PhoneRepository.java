package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, String> {
}
