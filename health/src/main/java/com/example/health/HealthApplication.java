package com.example.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthApplication.class, args);
        System.out.println("✅ Health Queue Manager Backend Started!");
        System.out.println("✅ Server: http://localhost:8080");
        System.out.println("✅ API: http://localhost:8080/api");
    }
}