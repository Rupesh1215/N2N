package com.example.health.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public") // Changed from /api/public
public class TestController {
    
    @GetMapping("/test")
    public Map<String, Object> testEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Backend is working! 🎉");
        response.put("timestamp", LocalDateTime.now().toString());
        
        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("auth", "/auth/**");
        endpoints.put("health", "/health");
        endpoints.put("public", "/public/**");
        
        response.put("endpoints", endpoints);
        return response;
    }
}