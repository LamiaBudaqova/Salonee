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
   // private final TeamMemberService teamMemberService;
    //private final TestimonialService testimonialService;
    private final BlogService blogService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getHomeFeaturedCategories());
        model.addAttribute("about", aboutService.getAboutContent());
        model.addAttribute("navCategories", categoryService.getAllCategories());
        model.addAttribute("blogs", blogService.getLatestPosts());

        model.addAttribute("pageTitle", "Salone - Gözəllik və Spa");
        model.addAttribute("heroTitle", "Dəriyə qayğı, gözəlliyə yol");
        model.addAttribute("heroSubtitle", "Peşəkar komanda ilə premium xidmətlər");
        model.addAttribute("ctaText", "Rezervasiya et");
        return "index";
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("about", aboutService.getAboutContent());
        return "about";
    }
}
