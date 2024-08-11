package com.example.springsecurity_authority.service;

import com.example.springsecurity_authority.entity.Role;
import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.entity.UserRole;
import com.example.springsecurity_authority.repository.RoleRepository;
import com.example.springsecurity_authority.repository.UserRepository;
import com.example.springsecurity_authority.repository.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @PostConstruct
    public void init() {
        if (!"local".equals(activeProfile)) {
            return;
        }

        makeDefaultRoles();

        makeUserRole("user1@test.com", "user1", ADMIN_ROLE_NAME);
        makeUserRole("user2@test.com", "user2", DEVELOPER_ROLE_NAME);
        makeUserRole("user3@test.com", "user3", VIEWER_ROLE_NAME);
    }
    private final static String ADMIN_ROLE_NAME = "ADMIN";
    private final static String DEVELOPER_ROLE_NAME = "DEVELOPER";
    private final static String VIEWER_ROLE_NAME = "VIEWER";

    private void makeDefaultRoles() {
        // 명시적으로 생성
        roleRepository.save(Role.builder().roleName(ADMIN_ROLE_NAME).urlPattern("/admin/**").systemRoleYn("Y").build());
        roleRepository.save(Role.builder().roleName(DEVELOPER_ROLE_NAME).urlPattern("/developer/**").systemRoleYn("Y").build());
        roleRepository.save(Role.builder().roleName(VIEWER_ROLE_NAME).urlPattern("/viewer/**").systemRoleYn("Y").build());
    }

    private void makeUserRole(String email, String name, String roleName) {
        Role role = roleRepository.findByRoleName(roleName).orElseThrow();
        User user = User.builder()
            .email(email).username(name).password("{noop}1234")
            .build();

        userRoleRepository.save(UserRole.builder()
            .user(user).role(role)
            .build());
    }

}
