package com.backend134.salon.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String dashboard(){
        return "admin/index";  // templates/admin/index.html
    }

    @GetMapping("/admin/tables")
    public String tables(){
        return "admin/table"; // templates/admin/table.html
    }

    @GetMapping("/admin/forms")
    public String forms(){
        return "admin/form-layout"; // templates/admin/form-layout.html
    }
}
