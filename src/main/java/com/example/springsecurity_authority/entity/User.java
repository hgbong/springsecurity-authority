package com.example.springsecurity_authority.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "pid_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String username; // for login id

    private String email;
    private String password;
    private String phoneNumber;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) // FIXME EAGER 설정안할 시, UserDetails 가져오는 과정에서 tx이 이미 닫혀서 session(tx) not exists 에러 발생
    // @Builder.Default // Builder에서는 항상 연관객체 할당
    private List<UserRole> userRoles = new ArrayList<>();

    @Builder
    public User(String username, String email, String password, String phoneNumber, List<UserRole> userRoles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;

        if(CollectionUtils.isEmpty(userRoles)) {
            userRoles = new ArrayList<>();
        }
        this.userRoles = userRoles;
    }
}

