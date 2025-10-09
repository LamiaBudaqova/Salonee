package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminTestimonialTextUpdateDto;
import com.backend134.salon.admin.services.AdminTestimonialTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/testimonial-text")
@RequiredArgsConstructor
public class AdminTestimonialTextController {

    private final AdminTestimonialTextService adminTestimonialTextService;

    @GetMapping
    public String editForm(Model model) {
        model.addAttribute("text", adminTestimonialTextService.getText());
        return "admin/testimonial/text-edit";
    }

    @PostMapping
    public String update(@ModelAttribute("text") AdminTestimonialTextUpdateDto dto) {
        adminTestimonialTextService.update(dto);
        return "redirect:/admin/testimonial-text?success";
    }
}

