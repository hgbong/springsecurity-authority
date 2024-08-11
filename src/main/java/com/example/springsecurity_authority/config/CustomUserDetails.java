package com.example.springsecurity_authority.config;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public CustomUserDetails(String email, String password, List<String> roles) {
        this.email = email;
        this.password = password;
        this.authorities = convertRoles(roles);
    }

    private Collection<? extends GrantedAuthority> convertRoles(List<String> roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            GrantedAuthority authority = () -> "ROLE_" + role;
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
