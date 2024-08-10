package com.example.springsecurity_authority.controller.authz;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/developer")
public class DeveloperController {

    @GetMapping
    public void listUsers() {

    }
}
