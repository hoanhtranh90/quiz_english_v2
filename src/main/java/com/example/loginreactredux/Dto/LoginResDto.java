package com.example.loginreactredux.Dto;

public class LoginResDto {
    private final String jwttoken;

    public LoginResDto(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }

    @Override
    public String toString() {
        return
                jwttoken;
    }
}
