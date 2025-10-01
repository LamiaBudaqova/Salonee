package com.backend134.salon.controllers;

import com.backend134.salon.services.FooterService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final FooterService footerService;

    @ModelAttribute
    public void addFooterToModel(Model model) {
        model.addAttribute("footer", footerService.getFooter());
    }
}
