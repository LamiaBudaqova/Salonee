package com.backend134.salon.controllers;

import com.backend134.salon.dtos.salonservice.SalonServiceDto;
import com.backend134.salon.models.SalonService;
import com.backend134.salon.services.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SalonServiceController {
    private final SalonServiceService salonServiceService;

    @GetMapping("/service/{id}")
    public String servicePage(@PathVariable Long id, Model model){
        SalonServiceDto service = salonServiceService.getServiceById(id);

        model.addAttribute("service", service);
        return "salonService";
    }
}