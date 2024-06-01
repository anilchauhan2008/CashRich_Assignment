package com.anilchauhan.cashrich_assignment.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anilchauhan.cashrich_assignment.services.ApiService;
import com.anilchauhan.cashrich_assignment.services.UserService;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;
    
    @Autowired
    private UserService userService;

    @GetMapping("/call-third-party")
    public String callThirdPartyApi() {
    	SecurityContext context = SecurityContextHolder. getContext(); 
    	Authentication authentication = context.getAuthentication(); 
    	String username = authentication.getName();
    	
    	Long userId = getUserIdByUsername(username);  // Implement this method to get user ID by username
        return apiService.callThirdPartyApi(userId);
    }

    private Long getUserIdByUsername(String username) {
    	return userService.getUserIdByUsername(username);
    }
}