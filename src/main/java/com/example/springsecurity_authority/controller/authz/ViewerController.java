package com.example.springsecurity_authority.controller.authz;

import com.example.springsecurity_authority.entity.User;
import com.example.springsecurity_authority.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/viewer")
public class ViewerController {
}
