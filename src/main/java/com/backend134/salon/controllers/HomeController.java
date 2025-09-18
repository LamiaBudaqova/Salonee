package com.backend134.salon.controllers;

import com.backend134.salon.services.AboutService;
import com.backend134.salon.services.SalonServiceService;
import org.springframework.ui.Model;
import com.backend134.salon.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryService categoryService;
    private final AboutService aboutService;
    private final SalonServiceService salonServiceService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categories", categoryService.getHomeFeaturedCategories());
        model.addAttribute("about", aboutService.getAboutContent());

        model.addAttribute("navCategories", categoryService.getAllCategories());

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
