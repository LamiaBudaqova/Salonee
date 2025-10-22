package com.backend134.salon.controllers;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;

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
    public String register(@ModelAttribute UserRegisterDto registerDto,
                           HttpServletRequest request) {
        // ✅ 1. İstifadəçini qeydiyyatdan keçirdirik
        boolean result = userService.register(registerDto);

        if (result) {
            try {
                // ✅ 2. Uğurlu qeydiyyatdan sonra avtomatik login edirik
                request.login(registerDto.getEmail(), registerDto.getPassword());

                // ✅ 3. Uğurlu login sonra ana səhifəyə yönləndiririk
                return "redirect:/";
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/login?autoLoginError";
            }
        } else {
            return "redirect:/register?error";
        }
    }
}