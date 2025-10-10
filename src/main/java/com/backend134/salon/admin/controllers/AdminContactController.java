package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminContactCreateDto;
import com.backend134.salon.admin.dtos.AdminContactUpdateDto;
import com.backend134.salon.admin.services.AdminContactService;
import com.backend134.salon.dtos.contact.ContactDto;
import com.backend134.salon.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/contacts")
@RequiredArgsConstructor
public class AdminContactController {

    private final AdminContactService adminContactService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("contacts", adminContactService.getAll());
        return "admin/contact/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("contact", new AdminContactCreateDto());
        return "admin/contact/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminContactCreateDto dto) {
        adminContactService.create(dto);
        return "redirect:/admin/contacts";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("contact", adminContactService.getById(id));
        return "admin/contact/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute AdminContactUpdateDto dto) {
        adminContactService.update(dto);
        return "redirect:/admin/contacts";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminContactService.delete(id);
        return "redirect:/admin/contacts";
    }
}