package com.example.springsecurity_authority.controller.dto;

import com.example.springsecurity_authority.entity.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
            .userId(user.getUserId())
            .username(user.getUsername())
            .email(user.getEmail())
            .roles(user.getUserRoles().stream()
                .map(ur ->
                    ur.getRole().getRoleName())
                .collect(Collectors.toList()))
            .build();
    }
}
