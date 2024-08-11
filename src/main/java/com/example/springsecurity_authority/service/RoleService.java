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

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public List<String> getRolesForUrlPattern(String requestURI) {
        return Arrays.asList("ADMIN","DEVELOPER");
    }
}
