package com.example.springsecurity_authority.controller;

import com.example.springsecurity_authority.controller.dto.SignupDto;
import com.example.springsecurity_authority.entity.Role;
import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.entity.UserRole;
import com.example.springsecurity_authority.repository.RoleRepository;
import com.example.springsecurity_authority.repository.UserRoleRepository;
import com.example.springsecurity_authority.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/admin")
    public void signupForAdmin(@RequestBody SignupDto dto) {
        User user = dto.makeUser();

        signupService.signup(user);
    }

    @PostMapping("/partner")
    public void signupForPartner(@RequestBody SignupDto dto) {
        User user = dto.makeUser();
        signupService.signup(user);
    }
}
