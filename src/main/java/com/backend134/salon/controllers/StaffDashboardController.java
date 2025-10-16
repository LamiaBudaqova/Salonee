package com.backend134.salon.controllers;

import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.services.StaffDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffDashboardController {

    private final StaffDashboardService staffDashboardService;

    // 🔹 Staff dashboard (RESTful + MVC pattern)
    @GetMapping("/dashboard/{staffId}")
    public String dashboard(@PathVariable Long staffId, Model model) {

        // ✅ Servis çağırırıq – DB əməliyyatları burada deyil!
        model.addAttribute("stats", staffDashboardService.getStatsForStaff(staffId));
        model.addAttribute("reservations", staffDashboardService.getReservationsForStaff(staffId));
        model.addAttribute("statuses", ReservationStatus.values());
        model.addAttribute("staffId", staffId);

        return "staff/dashboard"; // templates/staff/dashboard.html
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam Long reservationId,
                               @RequestParam ReservationStatus status,
                               @RequestParam Long staffId) {
        staffDashboardService.updateReservationStatus(reservationId, status);
        return "redirect:/staff/dashboard/" + staffId;
    }
}
