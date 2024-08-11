package com.example.springsecurity_authority.service;

import com.example.springsecurity_authority.entity.Role;
import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.entity.UserRole;
import com.example.springsecurity_authority.repository.RoleRepository;
import com.example.springsecurity_authority.repository.UserRepository;
import com.example.springsecurity_authority.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final static String DEFAULT_ROLE_NAME = "DEVELOPER"; // TODO 상수 따로 관리

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;


    public void signup(User user) {
        // 동일 이메일 가입 방지
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());
        if (userAlreadyJoined(opUser)) {
            throw new RuntimeException("user already exists");
        }

        Role defaultRole = roleRepository.findByRoleName(DEFAULT_ROLE_NAME).orElseThrow();

        UserRole userRole = UserRole.builder().user(user).role(defaultRole).build();
        userRoleRepository.save(userRole);
    }

    private boolean userAlreadyJoined(Optional<User> opUser) {
        return opUser.isPresent();
    }
}
