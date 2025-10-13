package com.backend134.salon.controllers;

import com.backend134.salon.dtos.reservation.ReservationCreateDto;
import com.backend134.salon.repositories.StaffRepository;
import com.backend134.salon.services.ReservationService;
import com.backend134.salon.services.SalonServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BookingController {

    private final ReservationService reservationService;
    private final SalonServiceService salonServiceService;
    private final StaffRepository staffRepository;

    // əsas form (xidmət seçilibsə — uyğun ustaları göstər)
    @GetMapping("/booking")
    public String form(@RequestParam(required = false) Long serviceId, Model model) {
        model.addAttribute("services", salonServiceService.getAll());
        model.addAttribute("reservation", new ReservationCreateDto());

        if (serviceId != null) {
            // yalnız seçilmiş xidməti görən ustalar
            model.addAttribute("staffList", staffRepository.findByServices_Id(serviceId));
        } else {
            // əgər heç nə seçilməyibsə, hələ hamısını göstər (və ya boş)
            model.addAttribute("staffList", staffRepository.findAll());
        }

        model.addAttribute("selectedServiceId", serviceId);
        return "booking";
    }

    // rezervasiya yaratmaq
    @PostMapping("/booking")
    public String create(@ModelAttribute @Valid ReservationCreateDto dto,
                         RedirectAttributes ra) {
        reservationService.create(dto);
        ra.addFlashAttribute("success", "Rezervasiya uğurla yaradıldı!");
        return "redirect:/booking";
    }
}
