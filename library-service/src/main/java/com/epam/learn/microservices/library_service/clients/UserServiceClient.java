package com.epam.learn.microservices.library_service.clients;

import com.epam.learn.microservices.library_service.DTO.LibraryDTO;
import com.epam.learn.microservices.user_service.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/users")
    List<LibraryDTO> getAllUsers();

    @GetMapping("/users/{username}")
    List<LibraryDTO> getUserByUsername(@PathVariable("username") String username);

    @PostMapping("/users")
    void addUser(@RequestBody UserDTO user);

    @DeleteMapping("/users/{username}")
    void deleteUserByUsername(@PathVariable("username") String username);

    @PutMapping("/users/{username}")
    void updateUserByUsername(@PathVariable("username")String username, @RequestBody UserDTO user);

    @PostMapping("/users/{username}/books/{bookId}")
    void addBookToUsernameById(@PathVariable("username")String username,@PathVariable("bookId")long id);

    @DeleteMapping("/users/{username}/books/{bookId}")
    void deleteBookFromUsernameById(@PathVariable("username")String username,@PathVariable("bookId")long id);
}
