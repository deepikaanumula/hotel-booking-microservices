package com.hotel.user_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.hotel.user_service.entity.UserProfile;
import com.hotel.user_service.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public UserProfile createUser(UserProfile user) {
        return repo.save(user);
    }

    public UserProfile getUserById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public List<UserProfile> getAllUsers() {
        return repo.findAll();
    }
}
