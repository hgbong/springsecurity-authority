package com.example.springsecurity_authority.config.filter;

import com.example.springsecurity_authority.service.RoleService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    private final RoleService roleService;
    private final List<String> permitAllUrls;

    public CustomAuthorizationFilter(RoleService roleService, List<String> permitAllUrls) {
        this.roleService = roleService;
        this.permitAllUrls = permitAllUrls;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // permitAll로 지정된 URL인지 확인
        if (isPermitAllUrl(requestURI)) {
            filterChain.doFilter(request, response); // permitAll URL이면 필터 체인 계속 진행
            return;
        }

        // DB에서 URL 패턴에 매칭되는 Role 목록을 가져옴
        List<String> rolesForUrl = roleService.getRolesForUrlPattern(requestURI);

        // 현재 인증된 사용자의 권한을 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean hasAuthority = authorities.stream()
                .anyMatch(authority -> rolesForUrl.stream().anyMatch(r -> authority.getAuthority().equals("ROLE_"+r)));

            // TODO 계층구조 검사, 성능고려를 위한 캐시 사용, HA구성에서 캐시 분산 저장소 사옹

            if (hasAuthority) {
                filterChain.doFilter(request, response); // 권한이 있으면 필터 체인 계속 진행
                return;
            }
        }

        // 권한이 없는 경우 403 Forbidden 응답
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "You don't have the necessary authority.");
    }

    private PathMatcher pathMatcher = new AntPathMatcher();
    private boolean isPermitAllUrl(String requestURI) {
        return permitAllUrls.stream().anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    }
}
