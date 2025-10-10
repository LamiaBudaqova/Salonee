package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminTeamMemberCreateDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberResponseDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberUpdateDto;
import com.backend134.salon.admin.services.AdminTeamMemberService;
import com.backend134.salon.models.TeamMember;
import com.backend134.salon.repositories.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminTeamMemberServiceImpl implements AdminTeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminTeamMemberResponseDto> getAll() {
        return teamMemberRepository.findAll()
                .stream()
                .map(m -> modelMapper.map(m, AdminTeamMemberResponseDto.class))
                .toList();
    }

    @Override
    public AdminTeamMemberResponseDto getById(Long id) {
        TeamMember member = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Üzv tapılmadı"));
        return modelMapper.map(member, AdminTeamMemberResponseDto.class);
    }

    @Override
    public void create(AdminTeamMemberCreateDto dto) {
        TeamMember member = modelMapper.map(dto, TeamMember.class);
        teamMemberRepository.save(member);
    }

    @Override
    public void update(Long id, AdminTeamMemberUpdateDto dto) {
        TeamMember member = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Üzv tapılmadı"));
        member.setName(dto.getName());
        member.setPosition(dto.getPosition());
        member.setImageUrl(dto.getImageUrl());
        member.setFacebook(dto.getFacebook());
        member.setInstagram(dto.getInstagram());
        teamMemberRepository.save(member);
    }

    @Override
    public void delete(Long id) {
        teamMemberRepository.deleteById(id);
    }
}
