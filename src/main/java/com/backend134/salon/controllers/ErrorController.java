package com.backend134.salon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error-403")
    public String accessDenied() {
        return "error/error-403"; //  templates/error/error-403.html
    }
}
