package com.example.springsecurity.service;

import com.example.springsecurity.entities.User;
import com.example.springsecurity.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEventListener {

    private static final int MAX_ATTEMPT = 4;

    private final UserRepository userRepository;

    @EventListener
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        String email = event.getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found !"));
        int numberAttempt = user.getNumberAttempt();
        if (numberAttempt < MAX_ATTEMPT) {
            numberAttempt++;
            user.setNumberAttempt(numberAttempt);
            userRepository.save(user);
        } else {
            user.setNonLockAccount(false);
            userRepository.save(user);
            log.info("Account :{} has many attempt !", email);
        }
    }
}
