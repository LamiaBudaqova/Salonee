package com.backend134.salon.controllers;

import com.backend134.salon.repositories.TeamMemberRepository;
import com.backend134.salon.services.TeamMemberService;
import com.backend134.salon.services.TeamTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamMemberService teamMemberService;
    private final TeamTextService teamTextService;

    @GetMapping("/team")
    public String teamPage(Model model) {
        model.addAttribute("members", teamMemberService.getAllTeamMembers());
        model.addAttribute("teamText", teamTextService.getText());

        return "team";
    }
}
