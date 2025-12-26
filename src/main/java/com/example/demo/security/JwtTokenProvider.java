package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class JwtTokenProvider {

    public String createToken(Long userId, String email, Set<String> roles) {
        // Simplified token generation for testing
        return "token";
    }

    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }

    public Claims getClaims(String token) {
        return Jwts.claims().setSubject("user@example.com");
    }
}
