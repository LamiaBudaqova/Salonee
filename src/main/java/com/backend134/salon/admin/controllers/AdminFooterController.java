package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminFooterCreateDto;
import com.backend134.salon.admin.dtos.AdminFooterResponseDto;
import com.backend134.salon.admin.dtos.AdminFooterUpdateDto;
import com.backend134.salon.admin.services.AdminFooterService;
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

    private final AdminFooterService adminFooterService;

    @GetMapping
    public String getFooter(Model model) {
        AdminFooterResponseDto footer = adminFooterService.getFooter();
        if (footer == null) footer = new AdminFooterResponseDto();
        model.addAttribute("footer", footer);
        return "admin/footer/form";
    }

    @PostMapping
    public String saveFooter(@ModelAttribute("footer") AdminFooterUpdateDto dto) {
        AdminFooterResponseDto existing = adminFooterService.getFooter();

        if (existing == null) {
            adminFooterService.createFooter(new AdminFooterCreateDto(
                    dto.getAddress(),
                    dto.getPhone(),
                    dto.getEmail(),
                    dto.getFacebookUrl(),
                    dto.getInstagramUrl(),
                    dto.getDescription()
            ));
        } else {
            adminFooterService.updateFooter(existing.getId(), dto);
        }

        return "redirect:/admin/footer?success";
    }
}