package com.example.springsecurity_authority.service;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @PostConstruct
    public void init() {
        if (!"local".equals(activeProfile)) {
            return;
        }

        userRepository.save(User.builder()
            .email("user@test.com").username("user").password("{noop}1234")
            .build());
    }

    public void signup(User user) {
        // 동일 이메일 가입 방지
        Optional<User> opUser = userRepository.findByEmail(user.getEmail());
        if (userAlreadyJoined(opUser)) {
            throw new RuntimeException("user already exists");
        }

        userRepository.save(user);
    }

    private boolean userAlreadyJoined(Optional<User> opUser) {
        return opUser.isPresent();
    }
}
