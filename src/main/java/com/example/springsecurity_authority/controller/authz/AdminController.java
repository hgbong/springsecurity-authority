package com.example.springsecurity_authority.controller.authz;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;

    @Transactional
    @PutMapping("/users/{userId}/roles/{roleName}") // CONSIDER: use request body
    public User changeUserRole(@PathVariable Long userId, @PathVariable String roleName) { // anti-pt (return entity)
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId not exists. input userId: " + userId));
        targetUser.changeRole(roleName);

        if (!isAdminRoleExists()) {
            throw new RuntimeException("admin must be exist at least one");
        }

        updateSessionForUserAuthChange(targetUser.getUsername(), Arrays.asList(roleName));
        return targetUser;
    }

    public void updateSessionForUserAuthChange(String username, List<String> newRoles) {
        // TODO : 권한 변경하더라도 session에 저장된 권한은 변경되지 않기 때문에, 재 로그인이 필요 (세션 재생성)
        // 사용성 개선을 위해, 권한이 변경된 경우 직접 해당 사용자 session 내 권한정보 변경 고려
    }

    private boolean isAdminRoleExists() {
        return userRepository.existsByRole("ADMIN");
    }
}
