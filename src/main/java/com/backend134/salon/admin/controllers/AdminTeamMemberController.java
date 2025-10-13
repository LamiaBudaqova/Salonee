package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminTeamMemberCreateDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberUpdateDto;
import com.backend134.salon.admin.services.AdminTeamMemberService;
import com.backend134.salon.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/team-members")
@RequiredArgsConstructor
public class AdminTeamMemberController {

    private final AdminTeamMemberService adminTeamMemberService;
    private final BranchService branchService;

    // 🔹 1. Siyahı (bütün komanda üzvləri)
    @GetMapping
    public String list(Model model) {
        model.addAttribute("members", adminTeamMemberService.getAll());
        return "admin/team/list";
    }

    // 🔹 2. Create formu
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("member", new AdminTeamMemberCreateDto());
        model.addAttribute("branches", branchService.getAllBranches());
        return "admin/team/create";
    }

    // 🔹 3. Yeni komanda üzvü yarat
    @PostMapping("/create")
    public String create(@ModelAttribute AdminTeamMemberCreateDto dto) {
        adminTeamMemberService.create(dto);
        return "redirect:/admin/team-members";
    }

    // 🔹 4. Edit formu
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var existing = adminTeamMemberService.getById(id);
        AdminTeamMemberUpdateDto dto = new AdminTeamMemberUpdateDto(
                existing.getName(),
                existing.getPosition(),
                existing.getImageUrl(),
                existing.getFacebook(),
                existing.getInstagram(),
                existing.getBranchId()
        );

        model.addAttribute("memberId", id);
        model.addAttribute("member", dto);
        model.addAttribute("branches", branchService.getAllBranches());
        return "admin/team/edit";
    }

    // 🔹 5. Mövcud komanda üzvünü yenilə
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute AdminTeamMemberUpdateDto dto) {
        adminTeamMemberService.update(id, dto);
        return "redirect:/admin/team-members";
    }

    // 🔹 6. Üzvü sil
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminTeamMemberService.delete(id);
        return "redirect:/admin/team-members";
    }
}
