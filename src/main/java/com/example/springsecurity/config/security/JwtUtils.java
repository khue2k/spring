package com.example.springsecurity.config.security;

import com.example.springsecurity.entities.User;
import com.example.springsecurity.reposiroty.TokenLogoutRepository;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class JwtUtils {

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value(value = "${app.jwtExpiration}")
    private Long jwtExpiration;
    @Value("${app.jwtCookie}")
    private String jwtCookie;
    @Value("${app.jwtRefreshExpiration}")
    private Long jwtRefreshExpiration;

    @Autowired
    private TokenLogoutRepository tokenLogoutRepository;

    //tạo ra token lưu trong cookies với flag là httpOnly
    public ResponseCookie generateJwtCookies(User user) {
        String jwt = generateToken(user.getEmail());
        return generateCookies(jwtCookie, jwt, "/api");
    }

    //tạo ra refresh token lưu trong cookies với flag httpOnly
    public ResponseCookie generateRefreshJwtCookies(String refreshToken) {
        return generateCookies(jwtCookie, refreshToken, "/path");
    }


    public ResponseCookie getCleanJwtCookies() {
        return ResponseCookie.from(jwtCookie, null).path("/api").build();
    }

    public ResponseCookie getCleanRefreshCookies() {
        return ResponseCookie.from(jwtCookie, null).path("/api/auth/refresh-token").build();
    }

    // tạo ra cookies
    private ResponseCookie generateCookies(String name, String value, String path) {
        return ResponseCookie.from(name, value).path(path).maxAge(24 * 60 * 60).httpOnly(true).build();
    }


    //lấy giá trị của cookies bằng name từ request
    private String getCookiesValueByName(HttpServletRequest request, String name) {
        Cookie cookie = WebUtils.getCookie(request, name);
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    // tạo thông tin từ user
    public String generateToken(String email) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expireDate)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    //lấy thông tin từ jwt
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    //kiểm tra token
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }


    public boolean checkTokenLogout(String token) {
        return tokenLogoutRepository.existsByValue(token);
    }

}
