package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminPriceCreateDto;
import com.backend134.salon.admin.dtos.AdminTestimonialCreateDto;
import com.backend134.salon.admin.dtos.AdminTestimonialUpdateDto;
import com.backend134.salon.admin.services.AdminTestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/testimonials")
@RequiredArgsConstructor
public class AdminTestimonialController {

    private final AdminTestimonialService adminTestimonialService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("testimonials", adminTestimonialService.getAll());
        return "admin/testimonial/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("testimonial", new AdminPriceCreateDto());
        return "admin/testimonial/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminTestimonialCreateDto dto){
        adminTestimonialService.create(dto);
        return "redirect:/admin/testimonials";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        model.addAttribute("testimonial", adminTestimonialService.getById(id));
        return "admin/testimonial/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminTestimonialUpdateDto dto){
        adminTestimonialService.update(id, dto);
        return "redirect:/admin/testimonials";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        adminTestimonialService.delete(id);
        return "redirect:/admin/testimonials";
    }
}
