package com.prasoon.library.library_service.feign;


import com.prasoon.library.library_service.model.Book;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import com.prasoon.library.library_service.dto.BookInputDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "book-service", url = "http://localhost:8080/books")
public interface BookFeignClient {

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks();

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id);

    @PostMapping
    public ResponseEntity<Book> postBook(@Valid @RequestBody BookInputDTO bookDto);

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id);

    @PatchMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Map<String, Object> updates);

    @GetMapping("/by-ids")
    public ResponseEntity<List<Book>> getBooksForArrayOfIds(@RequestBody List<Integer> ids);
}
