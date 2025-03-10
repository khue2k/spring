package com.example.springsecurity.utils;

import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.reposiroty.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Database implements ApplicationRunner {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Database(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
//        Role roleAdmin = new Role();
//        Role roleUser = new Role();
//        roleAdmin.setRoleName(ERole.ROLE_ADMIN);
//        roleUser.setRoleName(ERole.ROLE_USER);
//        Set<Role> rolesAdmin = new HashSet<>();
//        rolesAdmin.add(roleAdmin);
//        rolesAdmin.add(roleUser);
//        Set<Role> rolesUser = new HashSet<>();
//        rolesUser.add(roleUser);
//
//        User admin = new User();
//        admin.setPassword(passwordEncoder.encode("admin"));
//        admin.setEmail("admin@gmail.com");
//        admin.setNonLockAccount(true);
//        admin.setRoles(rolesAdmin);
//
//        User user1 = new User();
//        user1.setPassword(passwordEncoder.encode("user1"));
//        user1.setNonLockAccount(true);
//        user1.setEmail("user1@gmail.com");
//        user1.setRoles(rolesUser);
//
//        User user2 = new User();
//        user2.setPassword(passwordEncoder.encode("user2"));
//        user2.setNonLockAccount(true);
//        user2.setEmail("user2@gmail.com");
//        user2.setRoles(rolesUser);
//
//        List<User> users = Arrays.asList(admin, user1, user2);
//        userRepository.saveAll(users);
    }
}
