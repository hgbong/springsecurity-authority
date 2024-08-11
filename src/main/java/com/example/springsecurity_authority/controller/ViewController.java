package com.example.springsecurity_authority.controller;

import com.example.springsecurity_authority.controller.dto.UserResponseDto;
import com.example.springsecurity_authority.repository.RoleRepository;
import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class ViewController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("users", getUsersDto());
        model.addAttribute("roles", roleRepository.findAll());

        return "index";
    }

    @GetMapping("/manage-auth")
    public String manageAuth(Model model) {
        model.addAttribute("users", getUsersDto());

        return "manage-auth";
    }

    private List<UserResponseDto> getUsersDto() {
        return userRepository.findAll()
            .stream()
            .map(user -> UserResponseDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .userId(user.getUserId())
                .roles(user.getUserRoles().stream().map(ur -> ur.getRole().getRoleName()).collect(Collectors.toList()))
                .build())
            .collect(Collectors.toList());
    }
}
