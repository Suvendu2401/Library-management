package com.epam.learn.microservices.library_service.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryDTO {
    private long id;
    private String username;
    private long bookId;
}
