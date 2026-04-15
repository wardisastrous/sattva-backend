package com.sattva.sattva_backend.service;

import com.sattva.sattva_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    // Simple admin authentication
    // In production, you'd check database and hash passwords
    public String login(String username, String password) {
        if (adminUsername.equals(username) && adminPassword.equals(password)) {
            return jwtUtil.generateToken(username); // Return JWT token
        }
        throw new RuntimeException("Invalid credentials");
    }
}