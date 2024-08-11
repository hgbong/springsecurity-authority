package com.example.springsecurity_authority.config;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) {
            return null;
        }
        User user = optUser.get();
        List<String> roles = new ArrayList<>();
        user.getUserRoles().forEach(ur -> roles.add(ur.getRole().getRoleName()));

        return CustomUserDetails.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .roles(roles)
            .build();
    }
}
