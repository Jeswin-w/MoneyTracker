package com.saharaj.moneytracker.application.authentication.dto;

public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return this.token;
    } 

}
