package com.epam.learn.microservices.book_service.repository;


import com.epam.learn.microservices.book_service.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

}
