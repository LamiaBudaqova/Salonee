package com.backend134.salon.controllers;

import com.backend134.salon.services.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blog")
    public String bloglist(Model model){
        model.addAttribute("blogs", blogService.getLatestPosts());
        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getById(id));
        return "blog-detail";
    }
}
