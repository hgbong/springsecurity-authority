package com.example.springsecurity_authority.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pid_group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Group {
    @Id
    @GeneratedValue
    private Long groupId;

    private String groupname;
    private String tag;
    private String ownerId;

}