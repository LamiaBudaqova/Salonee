package com.backend134.salon.controllers;

import com.backend134.salon.services.*;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final AboutService aboutService;
    private final SalonServiceService salonServiceService;
    private final GalleryImageService galleryImageService;
    private final BlogService blogService;
    private final HeroService heroService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("activePage", "home");
        model.addAttribute("categories", categoryService.getHomeFeaturedCategories());
        model.addAttribute("about", aboutService.getAboutForHome()); // ana səhifə üçün qısaldılmış
        model.addAttribute("navCategories", categoryService.getAllCategories());
        model.addAttribute("blogs", blogService.getLatestPosts());
        model.addAttribute("hero", heroService.getHero());

        return "index";
    }

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("activePage", "about");
        model.addAttribute("about", aboutService.getAboutForPage()); // tam description
        model.addAttribute("hero", heroService.getHero());
        model.addAttribute("navCategories", categoryService.getAllCategories());
        return "about";
    }
}