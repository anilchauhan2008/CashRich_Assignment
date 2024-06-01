package com.anilchauhan.cashrich_assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anilchauhan.cashrich_assignment.entities.User;
import com.anilchauhan.cashrich_assignment.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	   @Autowired
	    private UserService userService;

	    @PostMapping("/signup")
	    public User registerUser(@Valid @RequestBody User user) throws Exception {
	        return userService.registerUser(user);
	    }

	    @PutMapping("/user/{id}")
	    public User updateUser(@PathVariable Long id, @Valid @RequestBody User user) throws Exception {
	        return userService.updateUser(id, user);
	    }
}
