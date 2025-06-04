package com.epam.learn.microservices.user_service.controller;


import com.epam.campus.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/health")
    public HealthResponse healthCheck(){
        return new HealthResponse("OK","User Service is running.");
    }
}

