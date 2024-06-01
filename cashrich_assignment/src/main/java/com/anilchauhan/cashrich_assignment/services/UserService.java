package com.anilchauhan.cashrich_assignment.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anilchauhan.cashrich_assignment.entities.User;
import com.anilchauhan.cashrich_assignment.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email already exists");
        }

        existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new Exception("Username already exists");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updatedUser) throws Exception {
        Optional<User> existingUser = userRepository.findById(userId);
        if (!existingUser.isPresent()) {
            throw new Exception("User not found");
        }

        User user = existingUser.get();
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setMobile(updatedUser.getMobile());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
        }
        return userRepository.save(user);
    }

    public Long getUserIdByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(User::getId).orElse(null); 
    }
}
