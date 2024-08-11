package com.example.springsecurity_authority.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "pid_user_role")

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class UserRole {
    @Id
    @GeneratedValue
    private Long userRoleId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // private String activeYn = "Y";
    // ...

    @Builder
    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        user.getUserRoles().add(this);
    }
}

