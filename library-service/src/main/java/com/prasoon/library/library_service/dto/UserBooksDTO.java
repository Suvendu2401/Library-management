package com.prasoon.library.library_service.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserBooksDTO {

    private String name;
    private List<String> bookNames;
}
