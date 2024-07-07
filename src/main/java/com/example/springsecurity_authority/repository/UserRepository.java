package com.example.springsecurity_authority.repository;

import com.example.springsecurity_authority.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email=:email")
    Optional<User> findByEmail(String email); // intellij 빌드 사용 시, -parameters 컴파일옵션 추가 및, out/ 디렉토리 삭제 후 재빌드 필요


    // type-unsafety example
    @Query("select count(u) >0 from User u where u.email=:email")
    Optional<User> findCountByEmail(String email);
}
