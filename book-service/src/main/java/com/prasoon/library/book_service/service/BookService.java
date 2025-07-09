package com.prasoon.library.book_service.service;

import com.prasoon.library.book_service.dto.BookInputDTO;
import com.prasoon.library.book_service.exception.ResourceNotFoundException;
import com.prasoon.library.book_service.model.Book;
import com.prasoon.library.book_service.repository.BookRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BookService {

    BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book from this id is not present"));
    }

    public Book saveBook(BookInputDTO bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setGenre(bookDto.getGenre());
        book.setTitle(bookDto.getTitle());
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Book updateBookById(int id, Map<String, Object> updates) {
        Book book = getBookById(id);

        if (updates.containsKey("title")) {
            book.setTitle((String) updates.get("title"));
        }
        if (updates.containsKey("author")) {
            book.setAuthor((String) updates.get("author"));
        }
        if (updates.containsKey("genre")) {
            book.setGenre((String) updates.get("genre"));
        }

        return bookRepository.save(book);
    }

    public List<Book> getBooksForArrayOfIds(List<Integer> ids) {
        return getAllBooks().stream()
                .filter(book -> ids.contains(book.getId()))
                .toList();
    }
}
