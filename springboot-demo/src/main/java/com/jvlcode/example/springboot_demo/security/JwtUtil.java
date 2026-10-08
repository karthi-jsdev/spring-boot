package com.jvlcode.example.springboot_demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
@Component
public class JwtUtil {

    private final SecretKey secretKey = Keys.hmacShaKeyFor(
            "my-super-secret-key-my-super-secret-key"
                    .getBytes(StandardCharsets.UTF_8)
    );

    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }
}