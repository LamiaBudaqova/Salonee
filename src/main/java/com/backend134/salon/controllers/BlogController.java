package com.backend134.salon.controllers;

import com.backend134.salon.dtos.blog.BlogDto;
import com.backend134.salon.services.BlogService;
import com.backend134.salon.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final CategoryService categoryService;

    @GetMapping("/blog")
    public String blogList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            Model model
    ) {

        Page<BlogDto> blogPage = blogService.getBlogsPage(page, size);
        int totalPages = blogPage.getTotalPages();

        if (page < 0) {
            return "redirect:/blog?page=0";
        }

        if (totalPages > 0 && page > totalPages - 1) {
            return "redirect:/blog?page=" + (totalPages - 1);
        }

        model.addAttribute("blogs", blogPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("navCategories", categoryService.getAllCategories());
        model.addAttribute("activePage", "blog");

        return "blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetail(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getById(id));
        model.addAttribute("navCategories", categoryService.getAllCategories());
        model.addAttribute("activePage", "blog");

        return "blog-detail";
    }
}
