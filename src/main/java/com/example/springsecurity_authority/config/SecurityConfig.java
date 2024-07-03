package com.example.springsecurity_authority.config;

import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/signup/**", "/error").permitAll()
                .requestMatchers("/users/**").permitAll() // for test
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/partner/**").hasRole("PARTNER") // TODO 역할 계층
                .anyRequest().authenticated())
            .formLogin(formLogin -> formLogin
                .usernameParameter("email"));

        return http.build();
    }

    // 굳이 여기서 선언 안하고, @Component CustomUserDetailsService.java 로 선언 가능
    private final UserRepository userRepository;
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepository);
    }
}
