package com.prasoon.library.library_service.feign;


import com.prasoon.library.library_service.model.User;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserFeignClient {
    @GetMapping("users")
    public ResponseEntity<List<User>> getAllUsers();

    @GetMapping("users/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name);

    @PostMapping("users")
    public ResponseEntity<User> postUser(@Valid @RequestBody User user);

    @DeleteMapping("users/{name}")
    public ResponseEntity<Void> deleteUser(@PathVariable String name);

    @PatchMapping("users/{name}")
    public ResponseEntity<User> updateUser(@PathVariable String name, @RequestBody Map<String, Object> updates);
}
