package com.anilchauhan.cashrich_assignment.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anilchauhan.cashrich_assignment.beans.LoginRequest;
import com.anilchauhan.cashrich_assignment.beans.LoginResponse;
import com.anilchauhan.cashrich_assignment.entities.User;
import com.anilchauhan.cashrich_assignment.repositories.UserRepository;
import com.anilchauhan.cashrich_assignment.secutiry.JwtProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public String authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());
        if (userOpt.isPresent() && bCryptPasswordEncoder.matches(loginRequest.getPassword(), userOpt.get().getPassword())) {
            return jwtProvider.generateToken(userOpt.get().getUsername());
        } else {
            throw new Exception("Invalid username or password");
        }
    }
}
