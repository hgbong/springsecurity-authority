package com.example.springsecurity_authority.controller;

import com.example.springsecurity_authority.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ViewController {
    private final UserRepository userRepository;
    public ViewController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "index";
    }

    @GetMapping("/manage-auth")
    public String manageAuth(Model model) {
        model.addAttribute("users", userRepository.findAll());

        return "manage-auth";
    }
}
