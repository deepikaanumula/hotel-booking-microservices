package com.hotel.auth_service.controller;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.hotel.auth_service.dto.LoginRequest;
import com.hotel.auth_service.dto.RegisterRequest;
import com.hotel.auth_service.entity.AuthUser;
import com.hotel.auth_service.jwtutil.JwtUtil;
import com.hotel.auth_service.repository.AuthUserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthUserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        AuthUser user = new AuthUser();
        user.setName(request.getName());        // use name instead of username
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole("USER");

        repository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // LOGIN (email OR phone)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        AuthUser user = repository.findByEmail(request.getIdentifier())
                .or(() -> repository.findByPhone(request.getIdentifier()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getRole());

        return ResponseEntity.ok(Map.of(
                "token", token,
                "role", user.getRole()
        ));
    }
}
