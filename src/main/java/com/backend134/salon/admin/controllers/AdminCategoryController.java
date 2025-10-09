package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminCategoryCreateDto;
import com.backend134.salon.admin.dtos.AdminCategoryUpdateDto;
import com.backend134.salon.admin.services.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", adminCategoryService.getAll());
        return "admin/category/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("category", new AdminCategoryCreateDto());
        return "admin/category/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminCategoryCreateDto dto) {
        adminCategoryService.create(dto);
        return "redirect:/admin/categories";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("category", adminCategoryService.getById(id));
        return "admin/category/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute AdminCategoryUpdateDto dto) {
        adminCategoryService.update(dto);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminCategoryService.delete(id);
        return "redirect:/admin/categories";
    }
}

