package com.example.springsecurity_authority.config;

import com.example.springsecurity_authority.config.filter.CustomAuthorizationFilter;
import com.example.springsecurity_authority.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final RoleService roleService;
    private final static List<String> permitAllUrls = List.of("/login", "/signup/**", "/error", "/h2-console/**", "/person/**", "/favicon.ico");
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(permitAllUrls.toArray(new String[0])).permitAll()

//                .requestMatchers("/developer/**").hasRole("DEVELOPER")
//                .requestMatchers("/viewer/**").hasRole("VIEWER")
//                .requestMatchers("/admin/**").hasRole("ADMIN") // "ROLE_" prefix 붙일 경우, app 구동 시 런타임 에러

                //.requestMatchers("/partner/**").hasRole("PARTNER") // TODO 역할 계층
                .anyRequest().authenticated())

            .formLogin(formLogin -> formLogin
                .usernameParameter("email"))
            .rememberMe(Customizer.withDefaults());

        http.addFilterAfter(new CustomAuthorizationFilter(roleService, permitAllUrls), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
            .role("ADMIN").implies("DEVELOPER", "VIEWER")
            .role("DEVELOPER").implies("VIEWER")
            .build();
    }
}
