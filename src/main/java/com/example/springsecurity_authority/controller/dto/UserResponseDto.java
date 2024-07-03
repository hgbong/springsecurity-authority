package com.example.springsecurity_authority.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String role;
}
