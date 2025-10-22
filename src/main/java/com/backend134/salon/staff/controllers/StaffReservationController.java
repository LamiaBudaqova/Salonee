package com.backend134.salon.staff.controllers;

import com.backend134.salon.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staff/reservations")
@RequiredArgsConstructor
public class StaffReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{id}/approve")
    public String approveReservation(@PathVariable Long id) {
        reservationService.approveReservation(id);
        return "redirect:/staff/reservations?approved";
    }

    @PostMapping("/{id}/reject")
    public String rejectReservation(@PathVariable Long id) {
        reservationService.rejectReservation(id);
        return "redirect:/staff/reservations?rejected";
    }
}
