package com.backend134.salon.controllers;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRegisterDto registerDto) {
        boolean result = userService.register(registerDto);

        if (result) {
            return "redirect:/login?success"; // qeydiyyatdan sonra login səhifəsinə yönləndir
        } else {
            return "redirect:/register?error"; // problem olsa yenidən register səhifəsinə
        }
    }
}
