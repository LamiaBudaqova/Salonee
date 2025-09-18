package com.backend134.salon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/services")
    public String services(){
        return "service";
    }

    @GetMapping("/pricing")
    public String pricing(){
        return "price";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
