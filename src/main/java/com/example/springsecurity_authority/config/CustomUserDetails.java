package com.example.springsecurity_authority.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(makeGrantedAuthorityType(role));
    }

    private GrantedAuthority makeGrantedAuthorityType(String role) {
        return () -> "ROLE_" + role;
//        return new GrantedAuthority() {
//            public String getAuthority() {
//                return role;
//            }
//        };
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


}
