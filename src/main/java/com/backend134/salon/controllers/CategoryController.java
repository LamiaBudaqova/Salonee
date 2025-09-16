package com.backend134.salon.controllers;

import com.backend134.salon.models.Category;
import com.backend134.salon.models.SalonService;
import com.backend134.salon.services.CategoryService;
import com.backend134.salon.services.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final SalonServiceService salonServiceService;

    @GetMapping("/category/{id}")
    public String categoryPage(@PathVariable Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        List<SalonService> services = salonServiceService.getServicesByCategoryId(id);

        model.addAttribute("category", category);
        model.addAttribute("services", services);

        return "category";
    }
}
