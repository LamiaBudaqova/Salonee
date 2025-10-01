package com.backend134.salon.controllers;

import com.backend134.salon.services.CategoryService;
import com.backend134.salon.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final CategoryService categoryService;
    private final LocationService locationService;

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("activePage", "contact");
        model.addAttribute("navCategories", categoryService.getAllCategories());

        // 🔹 DB-dən address, phone, email və mapLink gəlir
        model.addAttribute("locations", locationService.getAllLocations());

        return "contact";
    }
}
