package com.backend134.salon.controllers;

import com.backend134.salon.dtos.reservation.ReservationCreateDto;
import com.backend134.salon.repositories.StaffRepository;
import com.backend134.salon.services.ReservationService;
import com.backend134.salon.services.SalonServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final ReservationService reservationService;
    private final SalonServiceService salonServiceService;
    private final StaffRepository staffRepository;

    @GetMapping("/booking")
    public String form(Model model) {
        model.addAttribute("services", salonServiceService.getAll());
        model.addAttribute("staff", staffRepository.findAll());
        model.addAttribute("reservation", new ReservationCreateDto());
        return "booking";
    }

    @PostMapping("/booking")
    public String create(@ModelAttribute @Valid ReservationCreateDto dto,
                         RedirectAttributes ra) {
        Long id = reservationService.create(dto);
        ra.addFlashAttribute("success", "Rezervasiya uğurla yaradıldı!");
        return "redirect:/booking";
    }
}


