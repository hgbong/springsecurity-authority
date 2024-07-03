package com.example.springsecurity_authority.config;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByEmail(email);
        if (optUser.isEmpty()) {
            return null;
        }
        User user = optUser.get();

        return CustomUserDetails.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .role(user.getRole())
            .build();
    }
}
