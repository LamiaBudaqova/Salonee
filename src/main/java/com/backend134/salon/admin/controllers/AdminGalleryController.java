package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminGalleryImageCreateDto;
import com.backend134.salon.admin.dtos.AdminGalleryImageUpdateDto;
import com.backend134.salon.admin.services.AdminGalleryImageService;
import com.backend134.salon.dtos.gallery.GalleryTextDto;
import com.backend134.salon.services.GalleryTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/gallery")
@RequiredArgsConstructor
public class AdminGalleryController {

    private final AdminGalleryImageService adminGalleryImageService;
    private final GalleryTextService galleryTextService;

    // ---  Qalereya şəkillərinin siyahısı ---
    @GetMapping
    public String list(Model model) {
        model.addAttribute("images", adminGalleryImageService.getAll());
        return "admin/gallery/list"; // templates/admin/gallery/list.html
    }

    // ---  Yeni şəkil formu ---
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("image", new AdminGalleryImageCreateDto());
        return "admin/gallery/create"; // templates/admin/gallery/create.html
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminGalleryImageCreateDto dto) {
        adminGalleryImageService.create(dto);
        return "redirect:/admin/gallery";
    }

    // --- ✏ Redaktə formu ---
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("image", adminGalleryImageService.getById(id));
        return "admin/gallery/edit"; // templates/admin/gallery/edit.html
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute AdminGalleryImageUpdateDto dto) {
        adminGalleryImageService.update(id, dto);
        return "redirect:/admin/gallery";
    }

    // ---  Silmək ---
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminGalleryImageService.delete(id);
        return "redirect:/admin/gallery";
    }

    // --- Qalereya başlığı (title/subtitle) redaktəsi ---
    @GetMapping("/text")
    public String editText(Model model) {
        GalleryTextDto text = galleryTextService.getText();
        if (text == null) text = new GalleryTextDto();
        model.addAttribute("galleryText", text);
        return "admin/gallery/text"; // templates/admin/gallery/text.html
    }

    @PostMapping("/text")
    public String updateText(@ModelAttribute GalleryTextDto dto) {
        // Əgər gələcəkdə service-də update metodu yazsan, bura əlavə ediləcək
        // galleryTextService.update(dto);
        return "redirect:/admin/gallery/text?success";
    }
}