package com.example.springsecurity_authority.repository;

import com.example.springsecurity_authority.entity.Role;
import com.example.springsecurity_authority.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
    boolean existsByRoleName(String roleName);
}
