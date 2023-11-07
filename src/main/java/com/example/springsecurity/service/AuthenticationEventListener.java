package com.example.springsecurity.service;

import com.example.springsecurity.entities.User;
import com.example.springsecurity.reposiroty.UserRepository;
import com.example.springsecurity.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationEventListener {

    private static final int MAX_ATTEMPT = 3;

    private final UserRepository userRepository;

    @EventListener
    @Transactional
    public void authenticationFailed(AuthenticationFailureBadCredentialsEvent event) {
        String email = event.getAuthentication().getPrincipal().toString();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(Constants.EMAIL_NOT_FOUND));
        int numberAttempt = user.getNumberAttempt();
        increaseFailedAttempt(user);
        if (numberAttempt >= MAX_ATTEMPT) {
            int lockTimeDuration = (numberAttempt - MAX_ATTEMPT + 1) * 10;
            lockUser(user, lockTimeDuration);
            log.info("Account :{} has many attempt !", email);
        }
    }


    @EventListener
    @Transactional
    public void authenticationSuccess(AuthenticationSuccessEvent event) {
        String email = ((org.springframework.security.core.userdetails.User) event.getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(Constants.EMAIL_NOT_FOUND));
        resetFailedAttempt(user);
    }

    private void increaseFailedAttempt(User user) {
        int newFailedAttempt = user.getNumberAttempt() + 1;
        user.setNumberAttempt(newFailedAttempt);
        userRepository.updateFailedAttempts(newFailedAttempt, user.getEmail());
    }


    private void resetFailedAttempt(User user) {
        userRepository.updateFailedAttempts(0, user.getEmail());
    }

    private void lockUser(User user, int lockTimeDuration) {
        user.setNonLockAccount(false);
        user.setLockTime(new Date());
        user.setLockTimeDuration(lockTimeDuration);
        userRepository.save(user);
    }

}
