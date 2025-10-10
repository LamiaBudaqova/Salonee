package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminTeamMemberCreateDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberUpdateDto;
import com.backend134.salon.admin.services.AdminTeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/team-members")
@RequiredArgsConstructor
public class AdminTeamMemberController {

    private final AdminTeamMemberService adminTeamMemberService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", adminTeamMemberService.getAll());
        return "admin/team/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("member", new AdminTeamMemberCreateDto());
        return "admin/team/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminTeamMemberCreateDto dto) {
        adminTeamMemberService.create(dto);
        return "redirect:/admin/team-members";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var existing = adminTeamMemberService.getById(id);
        AdminTeamMemberUpdateDto dto = new AdminTeamMemberUpdateDto(
                existing.getName(),
                existing.getPosition(),
                existing.getImageUrl(),
                existing.getFacebook(),
                existing.getInstagram()
        );
        model.addAttribute("memberId", id);
        model.addAttribute("member", dto);
        return "admin/team/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminTeamMemberUpdateDto dto) {
        adminTeamMemberService.update(id, dto);
        return "redirect:/admin/team-members";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminTeamMemberService.delete(id);
        return "redirect:/admin/team-members";
    }
}
