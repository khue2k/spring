package com.example.springsecurity.reposiroty.repoImpl;

import com.example.springsecurity.reposiroty.UserCustomRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {
    @Override
    public String test() {
        return null;
    }
}
