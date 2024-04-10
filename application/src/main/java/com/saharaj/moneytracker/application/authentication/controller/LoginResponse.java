package com.saharaj.moneytracker.application.authentication.controller;

public class LoginResponse {
    private String token;

    LoginResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return this.token;
    } 

}
