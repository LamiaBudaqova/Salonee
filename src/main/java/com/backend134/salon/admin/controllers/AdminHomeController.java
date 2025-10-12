package com.backend134.salon.admin.controllers;

import com.backend134.salon.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AdminHomeController {

    private final HeroService heroService;
    private final AboutService aboutService;
    private final SalonServiceService salonServiceService;
    private final PriceService priceService;
    private final GalleryTextService galleryTextService;
    private final GalleryImageService galleryImageService;
    private final TeamTextService teamTextService;
    private final TeamMemberService teamMemberService;
    private final TestimonialTextService testimonialTextService;
    private final TestimonialService testimonialService;
    private final BlogService blogService;
    private final FooterService footerService;
    private final ContactService contactService;

    @GetMapping("/admin/home")
    public String home(Model model) {
        model.addAttribute("hero", heroService.getHero());
        model.addAttribute("about", aboutService.getAboutForHome());
        model.addAttribute("services", salonServiceService.getServicesByCategoryId(1L));
        model.addAttribute("prices", priceService.getHomePrices());
        model.addAttribute("galleryText", galleryTextService.getText());
        model.addAttribute("galleryImages", galleryImageService.getLimitedImages(6));
        model.addAttribute("teamText", teamTextService.getText());
        model.addAttribute("teamMembers", teamMemberService.getFirstFourMembers());
        model.addAttribute("testimonialText", testimonialTextService.getText());
        model.addAttribute("testimonials", testimonialService.getLatestFiveTestimonials());
        model.addAttribute("blogs", blogService.getLatestPosts());
        model.addAttribute("contacts", contactService.getAllContacts());
        model.addAttribute("footer", footerService.getFooter());

        return "admin/home";
    }
}
