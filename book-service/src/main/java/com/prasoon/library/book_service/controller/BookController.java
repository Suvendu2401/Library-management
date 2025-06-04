package com.prasoon.library.book_service.controller;


import com.prasoon.library.book_service.dto.BookInputDTO;
import com.prasoon.library.book_service.model.Book;
import com.prasoon.library.book_service.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("books")
@AllArgsConstructor
public class BookController {

    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> postBook(@Valid @RequestBody BookInputDTO bookDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(bookService.updateBookById(id, updates));
    }

    @GetMapping("/by-ids")
    public ResponseEntity<List<Book>> getBooksForArrayOfIds(@RequestBody List<Integer> ids){
        return ResponseEntity.ok(bookService.getBooksForArrayOfIds(ids));
    }
}
