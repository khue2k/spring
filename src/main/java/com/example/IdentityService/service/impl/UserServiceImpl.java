package com.example.IdentityService.service.impl;

import com.example.IdentityService.dtos.JwtResponseDTO;
import com.example.IdentityService.dtos.UserDTO;
import com.example.IdentityService.entities.*;
import com.example.IdentityService.exception.ExistEmailException;
import com.example.IdentityService.exception.ExistUsernameException;
import com.example.IdentityService.exception.ServerException;
import com.example.IdentityService.reposiroty.*;
import com.example.IdentityService.service.EmailService;
import com.example.IdentityService.service.RefreshTokenService;
import com.example.IdentityService.service.UserService;
import com.example.IdentityService.utils.ERole;
import com.example.IdentityService.config.security.JwtUtils;
import com.example.IdentityService.utils.Utils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    AuthenticationManager authenticationManager;

    PasswordEncoder passwordEncoder;

    RoleRepository roleRepository;

    JwtUtils jwtUtils;

    EmailService emailService;

    ConfirmationRepository confirmationRepository;

    ConfirmPasswordRepository confirmPasswordRepository;

    TokenLogoutRepository tokenLogoutRepository;

    RefreshTokenRepository refreshTokenRepository;

    RefreshTokenService refreshTokenService;

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
    public void changePassword(String token, UserDTO userDTO) {
        Optional<ConfirmPassword> confirmPasswordOptional = confirmPasswordRepository.findByToken(token);
        if (confirmPasswordOptional.isPresent()) {
            ConfirmPassword confirmPassword = confirmPasswordOptional.get();
            User user = userRepository.findByEmail(confirmPassword.getUser().getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email not found !"));
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public boolean logOut(String jwtToken) {
        if (!jwtUtils.validateToken(jwtToken)) {
            throw new ServerException("Token invalid");
        }
        if (tokenLogoutRepository.existsByValue(jwtToken)) {
            throw new ServerException("Token already logged out");
        }
        TokenLogout tokenLogout = new TokenLogout();
        tokenLogout.setCreateAt(new Date());
        tokenLogout.setCreateBy(jwtUtils.getUsername(jwtToken));
        tokenLogout.setValue(jwtToken);
        tokenLogoutRepository.save(tokenLogout);
        return true;
    }

    @Override
    public JwtResponseDTO refreshToken(String refreshToken) {
        JwtResponseDTO responseDTO = new JwtResponseDTO();
        String jwtToken;
        // find anh check refresh token expiration
        RefreshToken refreshToken1 = refreshTokenService.findValidByToken(refreshToken);

        //delete old refresh token
        refreshTokenRepository.delete(refreshToken1);

        //create new jwt token
        User user = refreshToken1.getUserInfo();
        jwtToken = jwtUtils.generateToken(user.getEmail());
        //create new refresh token
        RefreshToken refreshTokenNew = refreshTokenService.createRefreshToken(user.getEmail());
        refreshTokenRepository.save(refreshTokenNew);

        responseDTO.setJwt(jwtToken);
        responseDTO.setRefreshToken(refreshTokenNew.getToken());
        return responseDTO;
    }

    @Override
    public String auth(UserDTO userDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
