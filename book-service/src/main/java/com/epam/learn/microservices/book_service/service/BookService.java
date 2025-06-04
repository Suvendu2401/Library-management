package com.epam.learn.microservices.book_service.service;

import com.epam.learn.microservices.book_service.DTO.BookDTO;
import com.epam.learn.microservices.book_service.model.Book;
import com.epam.learn.microservices.book_service.repository.BookRepository;
import com.epam.learn.microservices.book_service.exception.ResourceNotFoundException;
import java.util.List;

public class BookService {
    BookRepository repository;

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book getBookById(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found with Book_id:" + id));
    }

    public Book addBook(BookDTO bookDto){
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setPublisher(bookDto.getPublisher());
        return repository.save(book);
    }

    public void deleteBookById(long id){
        repository.deleteById(id);
    }

    public Book updateBook(long id, BookDTO bookDto){

    }


}
