package com.epam.learn.microservice.api_gateway.controller;

import com.epam.campus.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping("/health")
    public HealthResponse healthCheck(){
        return new HealthResponse("OK","API Service is running");
    }
}
