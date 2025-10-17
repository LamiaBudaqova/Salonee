package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminStaffCreateDto;
import com.backend134.salon.admin.dtos.AdminStaffUpdateDto;
import com.backend134.salon.admin.services.AdminStaffService;
import com.backend134.salon.repositories.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/staff")
@RequiredArgsConstructor
public class AdminStaffController {

    private final AdminStaffService adminStaffService;
    private final BranchRepository branchRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("staffList", adminStaffService.getAll());
        return "admin/staff/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("staff", new AdminStaffCreateDto());
        model.addAttribute("branches", branchRepository.findAll());
        return "admin/staff/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminStaffCreateDto dto) {
        adminStaffService.create(dto);
        return "redirect:/admin/staff";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("staff", adminStaffService.getById(id));
        model.addAttribute("branches", branchRepository.findAll());
        return "admin/staff/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute AdminStaffUpdateDto dto) {
        adminStaffService.update(dto);
        return "redirect:/admin/staff";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminStaffService.delete(id);
        return "redirect:/admin/staff";
    }
}
