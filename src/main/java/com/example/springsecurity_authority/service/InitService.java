package com.example.springsecurity_authority.service;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitService {
    private final UserRepository userRepository;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @PostConstruct
    public void init() {
        if (!"local".equals(activeProfile)) {
            return;
        }

        User user1 = makeUser("user1@test.com", "user1", "ADMIN");
        User user2 = makeUser("user2@test.com", "user2", "DEVELOPER");
        User user3 = makeUser("user3@test.com", "user3", "VIEWER");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    private User makeUser(String email, String name, String role) {
        return User.builder()
            .email(email).username(name).password("{noop}1234").role(role)
            .build();
    }

}
