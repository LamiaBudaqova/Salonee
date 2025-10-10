package com.backend134.salon.controllers;

import com.backend134.salon.services.CategoryService;
import com.backend134.salon.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final CategoryService categoryService;
    private final ContactService contactService;

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("activePage", "contact");
        model.addAttribute("navCategories", categoryService.getAllCategories());
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contact";
    }

}

