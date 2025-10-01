package com.backend134.salon.controllers;

import com.backend134.salon.dtos.footer.FooterCreateDto;
import com.backend134.salon.dtos.footer.FooterUpdateDto;
import com.backend134.salon.services.FooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/footer")
@RequiredArgsConstructor
public class AdminFooterController {
    private final FooterService footerService;

    // Footer detalları (əsas səhifə)
    @GetMapping
    public String footerDetails(Model model) {
        model.addAttribute("footer", footerService.getFooter());
        return "admin/footer/details"; // Thymeleaf template: admin/footer/details.html
    }

    // Yeni footer yaratmaq (normalda yalnız bir dəfə istifadə ediləcək)
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("footer", new FooterCreateDto());
        return "admin/footer/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute FooterCreateDto dto) {
        footerService.createFooter(dto);
        return "redirect:/admin/footer";
    }

    // Edit formu
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("footerId", id);
        model.addAttribute("footer", new FooterUpdateDto());
        return "admin/footer/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute FooterUpdateDto dto) {
        footerService.updateFooter(id, dto);
        return "redirect:/admin/footer";
    }
}

