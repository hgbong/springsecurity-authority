package com.example.springsecurity_authority.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "pid_role")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long roleId;

    private String roleName;
    private String urlPattern; // TODO 1:n 관계로 변경 (역할은 여러 url 패턴 매치를 사용할 수 있음)

    private String systemRoleYn = "N";
}

