package com.prasoon.library.user_service.service;

import com.prasoon.library.user_service.exception.ResourceNotFoundException;
import com.prasoon.library.user_service.model.User;
import com.prasoon.library.user_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findById(name)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String name) {
        userRepository.deleteById(name);
    }

    public User updateUser(String name, Map<String, Object> updates){
        User user = getUserByName(name);

        if (updates.containsKey("name")) {
            user.setName((String) updates.get("name"));
        }
        if (updates.containsKey("email")) {
            user.setEmail((String) updates.get("email"));
        }

        return userRepository.save(user);
    }
}
