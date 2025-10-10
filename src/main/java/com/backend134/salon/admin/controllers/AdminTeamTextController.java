package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminTeamTextUpdateDto;
import com.backend134.salon.admin.services.AdminTeamTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/team-text")
@RequiredArgsConstructor
public class AdminTeamTextController {
    private final AdminTeamTextService adminTeamTextService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("teamText", adminTeamTextService.getTeamText());
        return "admin/team-text/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminTeamTextUpdateDto dto) {
        adminTeamTextService.updateTeamText(id, dto);
        return "redirect:/admin/team-text?success";
    }
}
