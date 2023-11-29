package com.example.springsecurity.service.impl;

import com.example.springsecurity.dtos.UserDTO;
import com.example.springsecurity.entities.ConfirmPassword;
import com.example.springsecurity.entities.Confirmation;
import com.example.springsecurity.entities.Role;
import com.example.springsecurity.entities.User;
import com.example.springsecurity.exception.ExistEmailException;
import com.example.springsecurity.exception.ExistUsernameException;
import com.example.springsecurity.reposiroty.ConfirmPasswordRepository;
import com.example.springsecurity.reposiroty.ConfirmationRepository;
import com.example.springsecurity.reposiroty.RoleRepository;
import com.example.springsecurity.reposiroty.UserRepository;
import com.example.springsecurity.service.EmailService;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.utils.ERole;
import com.example.springsecurity.config.security.JwtUtils;
import com.example.springsecurity.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final JwtUtils jwtUtils;

    private final EmailService emailService;

    private final ConfirmationRepository confirmationRepository;

    private final ConfirmPasswordRepository confirmPasswordRepository;


    @Override
    public User saveUser(UserDTO userDTO) {
        try {
            if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
                throw new ExistEmailException();
            }
            User user = new User();
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setStatus(false);
            user.setCreateAt(new Date());
            user.setNonLockAccount(true);
            user.setCreateBy("Anonymous");

            Role role = roleRepository.findByRoleName(ERole.ROLE_USER).orElseThrow(Exception::new);
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);

            userRepository.save(user);

            Confirmation confirmation = new Confirmation(user);
            confirmationRepository.save(confirmation);

            /*TODO send email to verify account */
            new Thread(() -> emailService.sendEmailConfirmNewUser(userDTO.getLastName(), userDTO.getEmail(), confirmation.getToken())).start();
            return user;
        } catch (ExistEmailException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ExistUsernameException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private void sendEmail() {

    }

    @Override
    public boolean verifyToken(String token) {
        if (confirmationRepository.existsByToken(token)) {
            Confirmation confirmation = confirmationRepository.findByToken(token);
            User user = userRepository.findByEmail(confirmation.getUser().getEmail()).orElseThrow(() -> new UsernameNotFoundException("Account not found"));
            user.setStatus(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found !"));
        ConfirmPassword confirmPassword = new ConfirmPassword(user);
        confirmPasswordRepository.save(confirmPassword);
        /*To do send email */
        new Thread(() -> emailService.sendEmailForgotPassword(user.getLastName(), user.getEmail(), confirmPassword.getToken()));
    }

    @Override
    public boolean changePassword(String token, UserDTO userDTO) {
        Optional<ConfirmPassword> confirmPasswordOptional = confirmPasswordRepository.findByToken(token);
        if (confirmPasswordOptional.isPresent()) {
            ConfirmPassword confirmPassword = confirmPasswordOptional.get();
            User user = userRepository.findByEmail(confirmPassword.getUser().getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found !"));
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public String auth(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Login : " + SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(() -> new UsernameNotFoundException("Email not found !"));
        if (user.getNumberAttempt() > 0) {
            user.setNumberAttempt(0);
            userRepository.save(user);
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    @Override
    public User userInfo(String brearToken) {
        String email = jwtUtils.getUsername(Utils.getToken(brearToken));
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found !"));
    }
}
