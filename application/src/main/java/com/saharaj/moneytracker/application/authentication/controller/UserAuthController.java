package com.saharaj.moneytracker.application.authentication.controller;

import org.springframework.web.bind.annotation.RestController;

import com.saharaj.moneytracker.application.authentication.model.AppUser;
import com.saharaj.moneytracker.application.authentication.repositories.AppUserRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private AppUserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserAuthController(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public AppUser postMethodName(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);        
        return user;
    }

    @GetMapping("path")
    public String getMethodName() {
        return "otha";
    }
    
    

}
