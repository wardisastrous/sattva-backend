package com.sattva.sattva_backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

// @Component = Spring will create and manage this object automatically
@Component
public class JwtUtil {
    
    @Value("${jwt.secret}") // Reads from application.properties
    private String secret;
    
    @Value("${jwt.expiration}")
    private long expiration;

    // Create a signing key from our secret
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate a JWT token for a username
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)           // Who the token is for
            .setIssuedAt(new Date())         // When it was created
            .setExpiration(new Date(System.currentTimeMillis() + expiration)) // When it expires
            .signWith(getSigningKey())       // Sign it with our secret
            .compact();                      // Build the token string
    }

    // Extract username from token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    // Check if token is valid and not expired
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // Token is invalid or expired
        }
    }
}