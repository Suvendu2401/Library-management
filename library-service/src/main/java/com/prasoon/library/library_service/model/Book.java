package com.prasoon.library.library_service.model;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Book {

    private int id;

    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String genre;
}