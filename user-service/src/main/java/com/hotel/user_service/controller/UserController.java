package com.hotel.user_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.hotel.user_service.entity.UserProfile;
import com.hotel.user_service.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User API")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create new user")
    public UserProfile createUser(@RequestBody UserProfile user) {
        return service.createUser(user);
    }

    @GetMapping("/{id}")
    public UserProfile getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }


    @GetMapping
    @Operation(summary = "Get all users")
    public List<UserProfile> getAllUsers() {
          System.out.println("Handled by pod: " + System.getenv("HOSTNAME"));
        return service.getAllUsers();
    }
}
