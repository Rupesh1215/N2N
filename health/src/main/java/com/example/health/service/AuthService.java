package com.example.health.service;

import com.example.health.model.User;
import com.example.health.repository.UserRepository;
import com.example.health.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    public Map<String, Object> register(Map<String, String> request) {
        if (userRepository.existsByEmail(request.get("email"))) {
            throw new RuntimeException("Email already registered");
        }
        
        User user = new User();
        user.setEmail(request.get("email"));
        user.setPassword(passwordEncoder.encode(request.get("password")));
        user.setFullName(request.get("fullName"));
        user.setPhone(request.get("phone"));
        user.setRole("PATIENT"); // Default role
        user.setCreatedAt(LocalDateTime.now());
        
        user = userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getEmail());
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        response.put("message", "Registration successful");
        
        return response;
    }
    
    public Map<String, Object> login(Map<String, String> request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.get("email"),
                request.get("password")
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();
        
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        
        String token = jwtUtil.generateToken(user.getEmail());
        
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);
        response.put("message", "Login successful");
        
        return response;
    }
}