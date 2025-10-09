package com.backend134.salon.admin.controllers;

import com.backend134.salon.admin.dtos.AdminBlogCreateDto;
import com.backend134.salon.admin.dtos.AdminBlogResponseDto;
import com.backend134.salon.admin.dtos.AdminBlogUpdateDto;
import com.backend134.salon.admin.services.AdminBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/blogs")
@RequiredArgsConstructor
public class AdminBlogController {

    private final AdminBlogService adminBlogService;

    //  Blog siyahısı
    @GetMapping
    public String list(Model model) {
        model.addAttribute("blogs", adminBlogService.getAll());
        return "admin/blog/list"; // templates/admin/blog/list.html
    }

    //  Blog yaratma formu
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("blog", new AdminBlogCreateDto());
        return "admin/blog/create"; // templates/admin/blog/create.html
    }

    @PostMapping("/create")
    public String create(@ModelAttribute AdminBlogCreateDto dto) {
        adminBlogService.create(dto);
        return "redirect:/admin/blogs";
    }

    // Blog redaktə formu
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        // Mövcud blog məlumatını al
        AdminBlogResponseDto blog = adminBlogService.getById(id);

        // AdminBlogUpdateDto yaradıb mövcud məlumatları doldur
        AdminBlogUpdateDto dto = new AdminBlogUpdateDto();
        dto.setTitle(blog.getTitle());
        dto.setContent(blog.getContent());
        dto.setImagePath(blog.getImagePath());

        // Modelə əlavə et
        model.addAttribute("blogId", id);
        model.addAttribute("blog", dto);

        return "admin/blog/edit"; // templates/admin/blog/edit.html
    }


    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute AdminBlogUpdateDto dto) {
        adminBlogService.update(id, dto);
        return "redirect:/admin/blogs";
    }

    //  Blog silmək
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        adminBlogService.delete(id);
        return "redirect:/admin/blogs";
    }
}
