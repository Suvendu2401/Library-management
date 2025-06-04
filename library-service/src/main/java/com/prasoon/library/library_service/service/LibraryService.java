package com.prasoon.library.library_service.service;

import com.prasoon.library.library_service.dto.BookInputDTO;
import com.prasoon.library.library_service.dto.UserBooksDTO;
import com.prasoon.library.library_service.exception.IssueException;
import com.prasoon.library.library_service.feign.BookFeignClient;
import com.prasoon.library.library_service.feign.UserFeignClient;
import com.prasoon.library.library_service.model.Book;
import com.prasoon.library.library_service.model.Records;
import com.prasoon.library.library_service.model.User;
import com.prasoon.library.library_service.repository.RecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LibraryService {

    private RecordRepository repository;
    private BookFeignClient bookFeignClient;
    private UserFeignClient userFeignClient;


    public List<Book> getAllBooksFromLibrary() {
        return bookFeignClient.getAllBooks().getBody();
    }

    public Book getBookFromLibraryWithId(int id) {
        return bookFeignClient.getBookById(id).getBody();
    }

    public Book saveBookInLibrary(BookInputDTO bookInputDTO) {
        return bookFeignClient.postBook(bookInputDTO).getBody();
    }

    public Book updateBookInLibrary(int id, Map<String, Object> updates) {
        return bookFeignClient.updateBook(id, updates).getBody();
    }

    public String deleteBookInLibrary(int id) {
        List<Records> records = repository.findAll();
        for(Records rec : records){
            if(rec.getBookId() == id){
                repository.deleteById(rec.getId());
            }
        }

        bookFeignClient.deleteBook(id);
        return "Book has been deleted from Library";
    }

    public List<User> getAllUsersFromLibrary() {
        return userFeignClient.getAllUsers().getBody();
    }

    public UserBooksDTO getAllUserFromLibraryWithName(String name) {
        User user = userFeignClient.getUserByName(name).getBody();
        assert user != null;
        List<Records> records = repository.findByUserName(name);

        List<Integer> ids = records.stream().map(Records::getBookId).toList();
        List<Book> books = bookFeignClient.getBooksForArrayOfIds(ids).getBody();
        assert books != null;
        List<String> bookNames = books.stream().map(Book::getTitle).toList();

        UserBooksDTO userBooksDTO = new UserBooksDTO();
        userBooksDTO.setName(user.getName());
        userBooksDTO.setBookNames(bookNames);

        return userBooksDTO;
    }

    public User saveUserInLibrary(User user) {
        return userFeignClient.postUser(user).getBody();
    }

    public User updateUserInLibrary(String name, Map<String, Object> updates) {
        return userFeignClient.updateUser(name, updates).getBody();
    }

    public String deleteUserInLibrary(String name) {
        List<Records> records = repository.findAll();
        for(Records rec : records){
            if(rec.getUserName().equalsIgnoreCase(name)){
                repository.deleteById(rec.getId());
            }
        }

        userFeignClient.deleteUser(name);
        return "User has been deleted from Library";
    }

    public Records issueBook(String name, int id) {
        List<Records> records = repository.findByBookId(id);
        if(records == null || records.isEmpty()){
            Records rec = new Records();
            rec.setBookId(id);
            rec.setUserName(name);
            String[] dates = getDates();
            rec.setIssueDate(dates[0]);
            rec.setReturnDate(dates[1]);
            repository.save(rec);
            return rec;
        } else {
            throw new IssueException("Book is already issued");
        }
    }

    private String[] getDates() {
        Date today = new Date();

        // Formatter
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Format today's date
        String issueDate = sdf.format(today);

        // Add 7 days for return date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date returnDateObj = calendar.getTime();
        String returnDate = sdf.format(returnDateObj);
        return new String[]{issueDate, returnDate};
    }

    public String deleteIssuedBook(String name, int id) {
        List<Records> records = repository.findByBookId(id);
        if(records == null || records.isEmpty()){
            throw new IssueException("No issue has been made form this book");
        } else {
            repository.deleteAllById(records.stream().map(Records::getId).toList());
        }
        return "Issue Deleted";
    }


}
