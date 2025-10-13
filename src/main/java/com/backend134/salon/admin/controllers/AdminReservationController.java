package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminReservationUpdateDto;
import com.backend134.salon.admin.services.AdminReservationService;
import com.backend134.salon.enums.ReservationStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/reservations")
@RequiredArgsConstructor
public class AdminReservationController {

    private final AdminReservationService adminReservationService;

    // butun rezervasiyaları goster
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("reservations", adminReservationService.getAllReservations());
        model.addAttribute("statuses", ReservationStatus.values());
        return "admin/reservation/list"; // templates/admin/reservation/list.html
    }

    // Status yenileme
    @PostMapping("/update")
    public String updateStatus(@ModelAttribute @Valid AdminReservationUpdateDto dto,
                               RedirectAttributes ra) {
        adminReservationService.updateStatus(dto);
        ra.addFlashAttribute("success", "Status uğurla yeniləndi!");
        return "redirect:/admin/reservations";
    }
}
