package com.epam.learn.microservices.library_service.service;

import com.epam.learn.microservices.book_service.DTO.BookDTO;
import com.epam.learn.microservices.library_service.clients.BookServiceClient;
import com.epam.learn.microservices.library_service.clients.UserServiceClient;
import com.epam.learn.microservices.library_service.exception.BookNotFoundException;
import com.epam.learn.microservices.library_service.exception.UserNotFoundException;
import com.epam.learn.microservices.library_service.model.Library;
import com.epam.learn.microservices.library_service.repository.LibraryRepository;
import com.epam.learn.microservices.user_service.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;
    private final UserServiceClient userServiceClient;

    LibraryService(LibraryRepository libraryRepository, BookServiceClient bookServiceClient, UserServiceClient userServiceClient){
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
        this.userServiceClient = userServiceClient;
    }

    public String issueBookToUser(String username, long bookId){

        UserDTO user = userServiceClient.getUserByUsername(username);
        if(user == null){
            throw new UserNotFoundException("User not found: "+ username);
        }

        BookDTO book = bookServiceClient.getBookById(bookId);
        if(book == null){
            throw new BookNotFoundException("Book not found: " + bookId);
        }

        List<Library> issuedBooks = libraryRepository.findByUsername(username);
        if (issuedBooks.size() >=3){
            throw new RuntimeException("User already has maximum of 3 books issued");
        }

        Library library = new Library();
        library.setUsername(username);
        library.setBookId(bookId);
        libraryRepository.save(library);
        return "Book issued to :" + username;
    }
}
