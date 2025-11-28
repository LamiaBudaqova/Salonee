package com.backend134.salon.controllers;

import com.backend134.salon.services.BranchService;
import com.backend134.salon.services.CategoryService;
import com.backend134.salon.services.TeamMemberService;
import com.backend134.salon.services.TeamTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TeamController {

    private final TeamMemberService teamMemberService;
    private final TeamTextService teamTextService;
    private final BranchService branchService;
    private final CategoryService categoryService;

    @GetMapping("/team")
    public String teamPage(
            @RequestParam(value = "branchId", required = false) Long branchId,
            Model model
    ) {
        //  Filial siyahısını databazadan getiririk
        model.addAttribute("branches", branchService.getAllBranches());

        //  Komanda başlıqları (TeamText)
        model.addAttribute("teamText", teamTextService.getText());

        //  eger konkret filial seçilibse, hemin filialdakı ustaları göster
        if (branchId != null) {
            model.addAttribute("members", teamMemberService.getByBranch(branchId));
        } else {
            model.addAttribute("members", teamMemberService.getAllTeamMembers());
        }

        model.addAttribute("selectedBranchId", branchId);
        model.addAttribute("navCategories", categoryService.getAllCategories());
        model.addAttribute("activePage", "team");


        return "team";
    }
}
