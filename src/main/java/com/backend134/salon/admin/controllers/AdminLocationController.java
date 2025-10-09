package com.backend134.salon.admin.controllers;

import com.backend134.salon.dtos.location.LocationCreateDto;
import com.backend134.salon.dtos.location.LocationUpdateDto;
import com.backend134.salon.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/locations")
@RequiredArgsConstructor
public class AdminLocationController {
    private final LocationService locationService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("locations", locationService.getDashboardLocations());
        return "admin/location/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("location", new LocationCreateDto());
        return "admin/location/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute LocationCreateDto dto) {
        locationService.createLocation(dto);
        return "redirect:/admin/locations";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("location", locationService.getLocationById(id));
        return "admin/location/edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute LocationUpdateDto dto) {
        locationService.updateLocation(dto);
        return "redirect:/admin/locations";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return "redirect:/admin/locations";
    }
}

