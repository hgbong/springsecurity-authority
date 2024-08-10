package com.example.springsecurity_authority.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/signup/**", "/error", "/h2-console/**").permitAll()

                .requestMatchers("/developer/**").hasRole("DEVELOPER")
                .requestMatchers("/viewer/**").hasRole("VIEWER")
                .requestMatchers("/admin/**").hasRole("ADMIN") // "ROLE_" prefix 붙일 경우, app 구동 시 런타임 에러

                //.requestMatchers("/partner/**").hasRole("PARTNER") // TODO 역할 계층
                .anyRequest().authenticated())

            .formLogin(formLogin -> formLogin
                .usernameParameter("email"))
            .rememberMe(Customizer.withDefaults())
        ;

        return http.build();
    }
}
