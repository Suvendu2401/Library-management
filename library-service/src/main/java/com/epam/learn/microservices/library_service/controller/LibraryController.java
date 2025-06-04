package com.epam.learn.microservices.library_service.controller;

import com.epam.campus.dto.HealthResponse;
import com.epam.learn.microservices.library_service.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private LibraryService libraryService;

    @Autowired
    LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @GetMapping("/health")
    public HealthResponse healthCheck(){
        return new HealthResponse("OK","Library Service is running.");
    }

    @PostMapping("/users/{username}/books/{bookId}")
    public ResponseEntity<String> issueBook(@PathVariable("username")String username, @PathVariable("bookId")long bookId){
        String result = libraryService.issueBookToUser( username,bookId);
        return ResponseEntity.ok(result);
    }



}

