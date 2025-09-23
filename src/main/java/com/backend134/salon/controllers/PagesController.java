package com.backend134.salon.controllers;

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

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("images", galleryImageService.getAllImages());
        model.addAttribute("text", galleryTextService.getText());
        return "gallery";
    }


}
