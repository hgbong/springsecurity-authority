package com.example.springsecurity_authority.controller;

import com.example.springsecurity_authority.controller.dto.UserResponseDto;
import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public List<UserResponseDto> listUsers() {
        // FIXME 바로 DAO 접근 X
        return
            userRepository.findAll()
                .stream()
                .map(user -> UserResponseDto.builder()
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .userId(user.getUserId())
                    .role(user.getRole())
                    .build())
                .collect(Collectors.toList());
    }
}
