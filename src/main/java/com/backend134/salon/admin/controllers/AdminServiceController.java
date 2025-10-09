package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminServiceCreateDto;
import com.backend134.salon.admin.dtos.AdminServiceUpdateDto;
import com.backend134.salon.admin.services.AdminServiceService;
import com.backend134.salon.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/services")
@RequiredArgsConstructor
public class AdminServiceController {
    private final AdminServiceService adminServiceService;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("services", adminServiceService.getAll());
        return "admin/service/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("service", new AdminServiceCreateDto());
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/service/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminServiceCreateDto dto) {
        adminServiceService.create(dto);
        return "redirect:/admin/services";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("service", adminServiceService.getById(id));
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/service/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute AdminServiceUpdateDto dto) {
        adminServiceService.update(dto);
        return "redirect:/admin/services";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminServiceService.delete(id);
        return "redirect:/admin/services";
    }
}

