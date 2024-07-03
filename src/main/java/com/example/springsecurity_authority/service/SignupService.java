package com.example.springsecurity_authority.service;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final UserRepository userRepository;

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
