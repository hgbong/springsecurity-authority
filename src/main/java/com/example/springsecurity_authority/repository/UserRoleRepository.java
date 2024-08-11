package com.example.springsecurity_authority.repository;

import com.example.springsecurity_authority.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    void deleteByUser_UserId(Long userId);
}
