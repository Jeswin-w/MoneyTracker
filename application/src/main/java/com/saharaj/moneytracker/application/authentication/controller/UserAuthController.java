package com.saharaj.moneytracker.application.authentication.controller;

import org.springframework.web.bind.annotation.RestController;

import com.saharaj.moneytracker.application.authentication.model.AppUser;
import com.saharaj.moneytracker.application.authentication.repositories.AppUserRepository;
import com.saharaj.moneytracker.application.authentication.service.JwtService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private AppUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public UserAuthController(AppUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("register")
    public AppUser register(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);        
        return user;
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        AppUser user = userRepository
        .findUserByEmail(loginRequest.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("Invalid email/ password"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email/password");
        }
        //login success
        LoginResponse LoginResponse = new LoginResponse(JwtService.generateToken(user));
        return LoginResponse;
    }
    

    @GetMapping("path")
    public String getMethodName() {
        return "otha";
    }
    
    

}
