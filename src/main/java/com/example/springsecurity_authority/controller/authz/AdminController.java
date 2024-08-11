package com.example.springsecurity_authority.controller.authz;

import com.example.springsecurity_authority.controller.dto.UserResponseDto;
import com.example.springsecurity_authority.entity.Role;
import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.entity.UserRole;
import com.example.springsecurity_authority.repository.RoleRepository;
import com.example.springsecurity_authority.repository.UserRepository;
import com.example.springsecurity_authority.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    @PutMapping("/users/{userId}/roles")
    public UserResponseDto changeUserRole(@PathVariable Long userId, @RequestBody Map<String, Object> params) { // anti-pt (return entity)
        User targetUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("userId not exists. input userId: " + userId));

        List<String> roleNames = (List<String>) (params.get("roleNames"));
        if (CollectionUtils.isEmpty(roleNames)) {
            return null;
        }

        initUserRoles(targetUser); // TODO reafct
        for (String roleName : roleNames) {
            Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> new RuntimeException("role does not exists. input roleName: " + roleName));
            UserRole userRole = UserRole.builder()
                .user(targetUser).role(role).build();
            userRoleRepository.save(userRole);
        }

        if (!isAdminRoleExists()) {
            throw new RuntimeException("admin must be exist at least one");
        }

        updateSessionForUserAuthChange(targetUser.getUsername(), roleNames);
        return UserResponseDto.from(targetUser);
    }

    private void initUserRoles(User targetUser) {
        userRoleRepository.deleteByUser_UserId(targetUser.getUserId());
    }

    public void updateSessionForUserAuthChange(String username, List<String> newRoles) {
        // TODO : 권한 변경하더라도 session에 저장된 권한은 변경되지 않기 때문에, 재 로그인이 필요 (세션 재생성)
        // 사용성 개선을 위해, 권한이 변경된 경우 직접 해당 사용자 session 내 권한정보 변경 고려
    }

    private boolean isAdminRoleExists() {
        return roleRepository.existsByRoleName("ADMIN");
    }
}
