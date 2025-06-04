package com.epam.learn.microservice.eureka_server.controller;

import com.epam.campus.dto.HealthResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerHealthController {

    @GetMapping("/health")
    public HealthResponse healthCheck(){
        return new HealthResponse("OK","Eureka Service is running.");
    }
}
