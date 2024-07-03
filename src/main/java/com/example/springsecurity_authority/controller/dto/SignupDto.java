package com.example.springsecurity_authority.controller.dto;

import com.example.springsecurity_authority.entity.User;
import lombok.Data;

@Data
public class SignupDto {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;

    public User makeUser() {
        return User.builder()
            .username(username)
            .email(email)
            .password(password)
            .phoneNumber(phoneNumber)
            .build();
    }
}
