package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminBranchCreateDto;
import com.backend134.salon.admin.dtos.AdminBranchUpdateDto;
import com.backend134.salon.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/branches")
@RequiredArgsConstructor
public class AdminBranchController {

    private final BranchService branchService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("branches", branchService.getAllBranches());
        return "admin/branch/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("branch", new AdminBranchCreateDto());
        return "admin/branch/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminBranchCreateDto dto){
        branchService.create(dto);
        return "redirect:/admin/branches";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("branch", new AdminBranchUpdateDto());
        return "admin/branch/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminBranchUpdateDto dto) {
        branchService.update(id, dto);
        return "redirect:/admin/branches";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        branchService.delete(id);
        return "redirect:/admin/branches";
    }
}
