package com.backend134.salon.controllers;

import com.backend134.salon.dtos.contact.ContactMessageDto;
import com.backend134.salon.services.ContactMessageService;
import com.backend134.salon.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ContactMessageController {

    private final ContactMessageService contactMessageService;
    private final CategoryService categoryService; // ✅ əlavə etdik

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("activePage", "contact");
        model.addAttribute("contactMessage", new ContactMessageDto());
        model.addAttribute("navCategories", categoryService.getAllCategories()); // ✅ burda əlavə etdik
        return "contact";
    }

    @PostMapping("/contact")
    public String saveMessage(ContactMessageDto contactMessageDto, RedirectAttributes ra, Model model) {
        try {
            contactMessageService.saveMessage(contactMessageDto);
            ra.addFlashAttribute("success", "Mesajınız uğurla göndərildi.");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Mesaj göndərilərkən xəta baş verdi.");
            e.printStackTrace();
        }
        return "redirect:/contact";
    }
}
