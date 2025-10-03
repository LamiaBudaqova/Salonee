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
        boolean result = userService.register(registerDto);

        if (result) {
            String continueParam = request.getParameter("continue");
            if (continueParam != null && !continueParam.isBlank()) {
                return "redirect:/login?success&continue=" + continueParam;
            }
            return "redirect:/login?success";
        } else {
            String continueParam = request.getParameter("continue");
            if (continueParam != null && !continueParam.isBlank()) {
                return "redirect:/register?error&continue=" + continueParam;
            }
            return "redirect:/register?error";
        }
    }
}
