package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.team.TeamMemberDto;
import com.backend134.salon.models.TeamMember;
import com.backend134.salon.repositories.TeamMemberRepository;
import com.backend134.salon.services.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public List<TeamMemberDto> getAllTeamMembers() {
        List<TeamMember> members = teamMemberRepository.findAll();
        List<TeamMemberDto> dtos = new ArrayList<>();

        for (TeamMember member : members) {
            TeamMemberDto dto = new TeamMemberDto();
            dto.setName(member.getName());
            dto.setPosition(member.getPosition());
            dto.setImageUrl(member.getImageUrl());
            dto.setFacebook(member.getFacebook());
            dto.setInstagram(member.getInstagram());

            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<TeamMemberDto> getFirstFourMembers() {
        List<TeamMember> members = teamMemberRepository.findAll();
        List<TeamMemberDto> dtos = new ArrayList<>();

        // yalnız ilk 4-ü götürək
        for (int i = 0; i < members.size() && i < 4; i++) {
            TeamMember member = members.get(i);
            TeamMemberDto dto = new TeamMemberDto();
            dto.setName(member.getName());
            dto.setPosition(member.getPosition());
            dto.setImageUrl(member.getImageUrl());
            dto.setFacebook(member.getFacebook());
            dto.setInstagram(member.getInstagram());
            dtos.add(dto);
        }
        return dtos;
    }
}
