package com.epam.learn.microservices.library_service.clients;

import com.epam.learn.microservices.book_service.DTO.BookDTO;
import com.epam.learn.microservices.library_service.DTO.LibraryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "book-service")
public interface BookServiceClient {

    @GetMapping("/books")
    List<BookDTO> getAllBooks();

    @GetMapping("/books/{bookId}")
    BookDTO getBookById(@PathVariable("bookId") long id);

    @PostMapping("/books/{bookId}")
    void addBookById(@PathVariable("bookId")long id,@RequestBody BookDTO book);

    @PutMapping("/books/{bookId}")
    void updateBookById(@PathVariable("bookId") long id, @RequestBody BookDTO book);

    @DeleteMapping("/books/{bookId}")
    void deleteBookById(@PathVariable("bookId")long id);
}
