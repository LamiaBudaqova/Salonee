package com.backend134.salon.controllers;

import com.backend134.salon.services.TestimonialService;
import com.backend134.salon.services.TestimonialTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestimonialController {
    private final TestimonialService testimonialService;
    private final TestimonialTextService testimonialTextService;

    @GetMapping("/testimonial")
    private String testimonialPage(Model model){
        model.addAttribute("testimonials", testimonialService.getAllTestimonials());
        model.addAttribute("testimonialText", testimonialTextService.getText());

        return "testimonial";
    }
}
