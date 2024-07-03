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
            .password("{noop}"+password) // AuthenticationProvider NoPasswordEncoder 에러 -> 일단 암호화 안하므로 {noop}
            .phoneNumber(phoneNumber)
            .build();
    }
}
