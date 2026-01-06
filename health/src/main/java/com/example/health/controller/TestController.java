package com.example.health.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class TestController {
    
    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Health Queue Manager API");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("version", "1.0.0");
        return response;
    }
    
    @GetMapping("/test")
    public Map<String, Object> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Backend is working! 🎉");
        response.put("timestamp", LocalDateTime.now().toString());
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("auth", "/api/auth/**");
        endpoints.put("health", "/api/public/health");
        
        response.put("endpoints", endpoints);
        return response;
    }
}