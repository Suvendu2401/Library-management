package com.prasoon.library.library_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
    @NotNull
    private String name;

    @NotNull
    private String email;
}
