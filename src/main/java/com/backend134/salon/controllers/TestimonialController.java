package com.backend134.salon.controllers;

import com.backend134.salon.dtos.testimonial.TestimonialCreateDto;
import com.backend134.salon.services.TestimonialService;
import com.backend134.salon.services.TestimonialTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;
    private final TestimonialTextService testimonialTextService;

    @GetMapping("/testimonial")
    private String testimonialPage(Model model){
        model.addAttribute("testimonials", testimonialService.getAllTestimonials());
        model.addAttribute("testimonialText", testimonialTextService.getText());
        model.addAttribute("testimonialForm", new TestimonialCreateDto()); //    form üçün boş DTO
        return "testimonial";
    }

    @PostMapping("/testimonial")
    public String createTestimonial(@ModelAttribute("testimonialForm") TestimonialCreateDto dto) {
        testimonialService.createTestimonial(dto);
        return "redirect:/testimonial"; // redirect edirik ki səhifə yenilənsin
    }
}