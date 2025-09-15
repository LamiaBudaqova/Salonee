package com.backend134.salon.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {


    @GetMapping({"/haqqimizda", "/about", "/about.html"})
    public String about(){
        return "about";
    }

    @GetMapping("/xidmetler")
    public String services(){
        return "service";
    }

    @GetMapping("/qiymetler")
    public String pricing(){
        return "price";
    }

    @GetMapping("/elaqe")
    public String contact(){
        return "contact";
    }
}
