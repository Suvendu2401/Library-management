package com.epam.campus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class HealthResponse {
    private String status;
    private String message;
    private LocalDateTime timeStamp;

    public HealthResponse(String status, String message){
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
