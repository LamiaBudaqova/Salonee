package com.backend134.salon.controllers;

import com.backend134.salon.dtos.reservation.ReservationCreateDto;
import com.backend134.salon.services.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final ReservationService reservationService;
    private final SalonServiceService salonServiceService;
    private final StaffService staffService;
    private final BranchService branchService;

    // 🔹 Ana səhifə (sadəcə form)
    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        model.addAttribute("branches", branchService.getAll());
        model.addAttribute("reservation", new ReservationCreateDto());
        return "booking";
    }

    @GetMapping("/booking/staff-by-branch/{branchId}")
    @ResponseBody
    public List<Map<String, Object>> getStaffByBranch(@PathVariable Long branchId) {
        return staffService.getByBranch(branchId)
                .stream()
                .map(st -> Map.<String, Object>of("id", st.getId(), "fullName", st.getFullName()))
                .toList();
    }


    @GetMapping("/booking/services-by-staff/{staffId}")
    @ResponseBody
    public List<Map<String, Object>> getServicesByStaff(@PathVariable Long staffId) {
        return salonServiceService.getByStaff(staffId)
                .stream()
                .map(s -> Map.<String, Object>of("id", s.getId(), "name", s.getName()))
                .toList();
    }


    // 🔹 POST – rezervasiyanı yarat
    @PostMapping("/booking")
    public String createReservation(@ModelAttribute @Valid ReservationCreateDto dto,
                                    RedirectAttributes ra) {
        reservationService.create(dto);
        ra.addFlashAttribute("success", "Rezervasiya uğurla yaradıldı!");
        return "redirect:/booking";
    }
}
