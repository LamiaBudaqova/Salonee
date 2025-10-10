package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminAboutUpdateDto;
import com.backend134.salon.admin.services.AdminAboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/about")
@RequiredArgsConstructor
public class AdminAboutController {

    private final AdminAboutService adminAboutService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("about", adminAboutService.getAbout());
        return "admin/about/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminAboutUpdateDto dto) {
        adminAboutService.updateAbout(id, dto);
        return "redirect:/admin/about?success";
    }
}
