package com.example.springsecurity_authority.controller;

import com.example.springsecurity_authority.controller.dto.SignupDto;
import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignupController {
    private final SignupService signupService;

    @PostMapping("/admin")
    public void signupForAdmin(@RequestBody SignupDto dto) {
        User user = dto.makeUser();
        user.changeRole("ADMIN"); // TODO constant or db 관리
        signupService.signup(user);
    }

    @PostMapping("/partner")
    public void signupForPartner(@RequestBody SignupDto dto) {
        User user = dto.makeUser();
        user.changeRole("PARTNER");
        signupService.signup(user);
    }
}
