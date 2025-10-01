package com.backend134.salon.controllers;

import com.backend134.salon.services.CategoryService;
import com.backend134.salon.services.GalleryImageService;
import com.backend134.salon.services.GalleryTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class PagesController {

    private final GalleryImageService galleryImageService;
    private final GalleryTextService galleryTextService;
    private final CategoryService categoryService; // ✅ əlavə etdik

    @GetMapping("/services")
    public String services(Model model){
        model.addAttribute("activePage", "services");
        model.addAttribute("navCategories", categoryService.getAllCategories()); // ✅ əlavə etdik
        return "service";
    }

    @GetMapping("/price")
    public String price(Model model) {
        model.addAttribute("activePage", "price");
        model.addAttribute("navCategories", categoryService.getAllCategories()); // ✅ əlavə etdik
        return "price";
    }

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("activePage", "gallery");
        model.addAttribute("images", galleryImageService.getAllImages());
        model.addAttribute("text", galleryTextService.getText());
        model.addAttribute("navCategories", categoryService.getAllCategories()); // ✅ əlavə etdik
        return "gallery";
    }
}
