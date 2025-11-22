package com.backend134.salon.controllers;

import com.backend134.salon.security.SecurityUtil;
import com.backend134.salon.services.UserReservationService;
import com.backend134.salon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserReservationController {

    private final UserReservationService userReservationService;
    private final UserService userService;

    @GetMapping("/user")
    public String dashboard(Model model){
        String email = SecurityUtil.getLoggedUserEmail();
        model.addAttribute("user", userService.findByEmail(email));
        return "user/dashboard";
    }

    @GetMapping("/user/reservations")
    public String reservations(Model model) {
        String email = SecurityUtil.getLoggedUserEmail();
        Long userId = userService.findByEmail(email).getId();
        model.addAttribute("reservations",
                userReservationService.getUserReservations(userId));
        return "user/reservations";
    }

    @GetMapping("/user/cashback")
    public String cashback(Model model) {
        String email = SecurityUtil.getLoggedUserEmail();
        model.addAttribute("user", userService.findByEmail(email));
        return "user/cashback";
    }

    @GetMapping("/user/profile")
    public String profile(Model model) {
        String email = SecurityUtil.getLoggedUserEmail();
        model.addAttribute("user", userService.findByEmail(email));
        return "user/profile";
    }

    @GetMapping("/user/profile/edit")
    public String editProfile(Model model) {
        String email = SecurityUtil.getLoggedUserEmail();
        model.addAttribute("user", userService.findByEmail(email));
        return "user/profile-edit";
    }

    @PostMapping("/user/profile/edit")
    public String updateProfile(@RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String email
    ) {
        String loggedEmail = SecurityUtil.getLoggedUserEmail();
        var user = userService.findByEmail(loggedEmail);

        userService.updateProfile(user.getId(), name, surname, email);

        return "redirect:/user/profile?updated";
    }

}