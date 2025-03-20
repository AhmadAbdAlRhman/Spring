package com.project.demo.service;

import com.project.demo.dto.*;
import com.project.demo.model.Patron;
import lombok.RequiredArgsConstructor;
import com.project.demo.repository.PatronRepository;
import com.project.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PatronRepository patronRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
     @Autowired
    public AuthService(PasswordEncoder passwordEncoder, PatronRepository patronRepository, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.patronRepository = patronRepository;
        this.jwtUtil = jwtUtil;
    }
    // Registration Method
    public AuthResponse register(RegisterRequest request) {
        Optional<Patron> existingUser = patronRepository.findByUsername(request.getUsername());
        if (existingUser.isPresent()) {
            return new AuthResponse(null, "Email is already registered");
        }

        Patron patron = new Patron();
        patron.setUsername(request.getUsername());
        patron.setPassword(passwordEncoder.encode(request.getPassword()));

        patronRepository.save(patron);

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token, "User registered successfully");
    }

    
    // Login Method
    public AuthResponse login(LoginRequest request) {
        Optional<Patron> patron = patronRepository.findByUsername(request.getUsername());
        if (patron.isEmpty() || !passwordEncoder.matches(request.getPassword(), patron.get().getPassword())) {
            return new AuthResponse(null, "Invalid credentials");
        }

        String token = jwtUtil.generateToken(request.getUsername());
        return new AuthResponse(token, "Login successful");
    }
    
}
