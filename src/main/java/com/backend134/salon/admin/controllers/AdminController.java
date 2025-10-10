package com.backend134.salon.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/tables")
    public String tables() {
        return "admin/table";
    }

    @GetMapping("/admin/forms")
    public String forms() {
        return "admin/form-layout";
    }
}
