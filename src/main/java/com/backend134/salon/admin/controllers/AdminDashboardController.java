package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.services.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminDashboardController {

    private final AdminDashboardService adminDashboardService;

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("stats", adminDashboardService.getStatistics());
        return "admin/dashboard";
    }
}
