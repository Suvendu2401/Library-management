package com.prasoon.library.library_service.controller;


import com.prasoon.library.library_service.dto.BookInputDTO;
import com.prasoon.library.library_service.dto.UserBooksDTO;
import com.prasoon.library.library_service.model.Book;
import com.prasoon.library.library_service.model.Records;
import com.prasoon.library.library_service.model.User;
import com.prasoon.library.library_service.service.LibraryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("library")
@AllArgsConstructor
public class LibraryController {

    private LibraryService libraryService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooksFromLibrary(){
        return ResponseEntity.ok(libraryService.getAllBooksFromLibrary());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookFromLibraryWithId(@PathVariable int id){
        return ResponseEntity.ok(libraryService.getBookFromLibraryWithId(id));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> postBookInLibrary(@Valid @RequestBody BookInputDTO bookInputDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.saveBookInLibrary(bookInputDTO));
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<Book> updateBookInLibrary(@PathVariable int id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.updateBookInLibrary(id, updates));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<String> deleteBookInLibrary(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(libraryService.deleteBookInLibrary(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsersFromLibrary(){
        return ResponseEntity.ok(libraryService.getAllUsersFromLibrary());
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<UserBooksDTO> getAllUserFromLibraryWithName(@PathVariable String name){
        return ResponseEntity.ok(libraryService.getAllUserFromLibraryWithName(name));
    }

    @PostMapping("/users")
    public ResponseEntity<User> postUserInLibrary(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.saveUserInLibrary(user));
    }

    @PatchMapping("/users/{name}")
    public ResponseEntity<User> updateUserInLibrary(@PathVariable String name, @RequestBody Map<String, Object> updates){
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.updateUserInLibrary(name, updates));
    }

    @DeleteMapping("/users/{name}")
    public ResponseEntity<String> deleteUserInLibrary(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(libraryService.deleteUserInLibrary(name));
    }

    @PostMapping("/users/{name}/books/{id}")
    public ResponseEntity<Records> issueBookToUser(@PathVariable String name, @PathVariable int id){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.issueBook(name, id));
    }

    @DeleteMapping("/users/{name}/books/{id}")
    public ResponseEntity<String> deleteIssuedBook(@PathVariable String name, @PathVariable int id){
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryService.deleteIssuedBook(name, id));
    }
}
