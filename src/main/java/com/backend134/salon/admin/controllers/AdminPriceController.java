package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminPriceCreateDto;
import com.backend134.salon.admin.dtos.AdminPriceUpdateDto;
import com.backend134.salon.admin.services.AdminPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/prices")
@RequiredArgsConstructor
public class AdminPriceController {

    private final AdminPriceService adminPriceService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("prices", adminPriceService.getAll());
        return "admin/price/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("price", new AdminPriceCreateDto());
        return "admin/price/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminPriceCreateDto dto){
        adminPriceService.create(dto);
        return "redirect:/admin/prices";
    }

    // qiymet redakte formu
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        var price = adminPriceService.getById(id);
        AdminPriceUpdateDto dto = new AdminPriceUpdateDto(price.getServiceName(), price.getAmount());
        model.addAttribute("price", dto);
        model.addAttribute("priceId", id);
        return "admin/price/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminPriceUpdateDto dto) {
        adminPriceService.update(id, dto);
        return "redirect:/admin/prices";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminPriceService.delete(id);
        return "redirect:/admin/prices";
    }
}
