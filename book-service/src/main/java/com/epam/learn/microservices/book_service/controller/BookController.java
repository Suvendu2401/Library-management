package com.epam.learn.microservices.book_service.controller;

import com.epam.campus.dto.HealthResponse;
import com.epam.learn.microservices.book_service.DTO.BookDTO;
import com.epam.learn.microservices.book_service.model.Book;
import com.epam.learn.microservices.book_service.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    BookService bookService;

    @GetMapping("/health")
    public HealthResponse healthCheck(){
        return new HealthResponse("OK","Book Service is running.");
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{book_id}")
    public Book getBookById(@PathVariable("book_id")long id){
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody BookDTO bookDto){
         return bookService.addBook(bookDto);
    }

    @DeleteMapping("/books/{book_id}")
    public void deleteBookById(@PathVariable ("book_id")long id){
        bookService.deleteBookById(id);
    }

    @PatchMapping("/books/{book_id}")
    public void updateBook(@PathVariable("book_id")long id,@RequestBody BookDTO bookDto){
        bookService.updateBook( id, bookDto);
    }


}
