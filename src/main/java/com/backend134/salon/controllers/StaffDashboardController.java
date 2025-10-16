package com.backend134.salon.controllers;

import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.services.StaffDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('STAFF','ADMIN')")
public class StaffDashboardController {

    private final StaffDashboardService staffDashboardService;

    // 🔹 1. Ustanın əsas paneli (statistika + rezervasiyalar)
    @GetMapping("/dashboard/{staffId}")
    public String dashboard(@PathVariable Long staffId, Model model) {
        model.addAttribute("stats", staffDashboardService.getStatsForStaff(staffId));
        model.addAttribute("reservations", staffDashboardService.getReservationsForStaff(staffId));
        model.addAttribute("statuses", ReservationStatus.values());
        model.addAttribute("staffId", staffId);
        model.addAttribute("pageTitle", "Panel");
        return "staff/dashboard"; // ✅ templates/staff/dashboard.html
    }

    // 🔹 2. Status dəyişmək (PENDING → APPROVED → DONE)
    @PostMapping("/update-status")
    public String updateStatus(@RequestParam Long reservationId,
                               @RequestParam ReservationStatus status,
                               @RequestParam Long staffId) {
        staffDashboardService.updateReservationStatus(reservationId, status);
        return "redirect:/staff/dashboard/" + staffId;
    }

    // 🔹 3. Login olan ustanı avtomatik panelinə yönləndir
    @GetMapping("/dashboard")
    public String redirectToDashboard(org.springframework.security.core.Authentication authentication) {
        String username = authentication.getName(); // email
        var staffOpt = staffDashboardService.getProfileByUsername(username);

        if (staffOpt.isPresent()) {
            Long staffId = staffOpt.get().getId(); // ✅ DÜZGÜN olan hissə
            return "redirect:/staff/dashboard/" + staffId;
        }

        return "redirect:/"; // tapılmazsa ana səhifəyə
    }

    // 🔹 4. Profil səhifəsi
    @GetMapping("/profile")
    public String profile(org.springframework.security.core.Authentication authentication, Model model) {
        String username = authentication.getName();
        var profile = staffDashboardService.getStaffProfile(username);

        model.addAttribute("staff", profile);
        model.addAttribute("pageTitle", "Profilim");

        return "staff/profile"; // ✅ templates/staff/profile.html
    }

    // 🔹 5. Rezervasiyalar səhifəsi
    @GetMapping("/reservations")
    public String reservations(org.springframework.security.core.Authentication authentication, Model model) {
        String username = authentication.getName();
        var staffOpt = staffDashboardService.getProfileByUsername(username);

        if (staffOpt.isPresent()) {
            var staff = staffOpt.get();
            model.addAttribute("reservations", staffDashboardService.getReservationsForStaff(staff.getId()));
            model.addAttribute("staffId", staff.getId());
            model.addAttribute("pageTitle", "Rezervasiyalarım");
            return "staff/reservations"; // ✅ templates/staff/reservations.html
        }

        return "redirect:/";
    }
}
