package com.prasoon.library.user_service.controller;


import com.prasoon.library.user_service.model.User;
import com.prasoon.library.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok(userService.getUserByName(name));
    }

    @PostMapping
    public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name) {
        userService.deleteUser(name);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{name}")
    public ResponseEntity<User> updateUser(@PathVariable String name, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(userService.updateUser(name, updates));
    }
}
