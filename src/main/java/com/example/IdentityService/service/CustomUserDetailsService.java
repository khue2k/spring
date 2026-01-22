package com.example.IdentityService.service;

import com.example.IdentityService.entities.User;
import com.example.IdentityService.reposiroty.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("username or email not found : {} " + email));
        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString())).collect(Collectors.toSet());
        if (!user.isNonLockAccount())
            unlockWhenTimeExpired(user);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getStatus(), true, true, user.isNonLockAccount(), authorities);
    }

    private boolean unlockWhenTimeExpired(User user) {
        long lockTime = user.getLockTime().getTime();
        long now = System.currentTimeMillis();
        long lockTimeDuration = user.getLockTimeDuration() * 60 * 1000;
        if (lockTime + lockTimeDuration < now) {
            user.setNonLockAccount(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
