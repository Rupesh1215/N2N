package com.example.health.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Welcome to Health Queue Manager API");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "UP");
        response.put("version", "1.0.0");
        response.put("endpoints", Map.of(
            "health", "/health",
            "public_test", "/public/test",
            "auth_test", "/auth/test",
            "register", "/auth/register (POST)",
            "login", "/auth/login (POST)"
        ));
        return response;
    }
    
    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Health Queue Manager API");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("version", "1.0.0");
        return response;
    }
}