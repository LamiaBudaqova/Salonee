package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminTeamMemberCreateDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberResponseDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberUpdateDto;
import com.backend134.salon.admin.services.AdminTeamMemberService;
import com.backend134.salon.models.Branch;
import com.backend134.salon.models.TeamMember;
import com.backend134.salon.repositories.BranchRepository;
import com.backend134.salon.repositories.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminTeamMemberServiceImpl implements AdminTeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    // 🔹 Bütün komanda üzvlərini gətir
    @Override
    public List<AdminTeamMemberResponseDto> getAll() {
        return teamMemberRepository.findAll()
                .stream()
                .map(m -> {
                    AdminTeamMemberResponseDto dto = modelMapper.map(m, AdminTeamMemberResponseDto.class);
                    dto.setBranchName(m.getBranch() != null ? m.getBranch().getName() : "-");
                    return dto;
                })
                .toList();
    }

    // 🔹 ID-yə görə 1 üzvü tap
    @Override
    public AdminTeamMemberResponseDto getById(Long id) {
        TeamMember member = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Üzv tapılmadı!"));
        AdminTeamMemberResponseDto dto = modelMapper.map(member, AdminTeamMemberResponseDto.class);
        dto.setBranchName(member.getBranch() != null ? member.getBranch().getName() : "-");
        return dto;
    }

    // 🔹 Yeni komanda üzvü yarat
    @Override
    public void create(AdminTeamMemberCreateDto dto) {
        // Filial mütləq seçilməlidir
        if (dto.getBranchId() == null) {
            throw new RuntimeException("Filial seçilməlidir!");
        }

        // DTO-dan Entity-yə map et
        TeamMember member = modelMapper.map(dto, TeamMember.class);

        // Əgər təsadüfən id gəlirsə, sıfırla (Hibernate insert kimi davransın)
        member.setId(null);

        // Filial obyektini tap və əlavə et
        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Filial tapılmadı!"));
        member.setBranch(branch);

        // Yadda saxla
        teamMemberRepository.save(member);
    }

    // 🔹 Mövcud üzvü yenilə
    @Override
    public void update(Long id, AdminTeamMemberUpdateDto dto) {
        TeamMember member = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Üzv tapılmadı!"));

        member.setName(dto.getName());
        member.setPosition(dto.getPosition());
        member.setImageUrl(dto.getImageUrl());
        member.setFacebook(dto.getFacebook());
        member.setInstagram(dto.getInstagram());

        // Filial mütləq seçilməlidir
        if (dto.getBranchId() == null) {
            throw new RuntimeException("Filial seçilməlidir!");
        }

        Branch branch = branchRepository.findById(dto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Filial tapılmadı!"));
        member.setBranch(branch);

        teamMemberRepository.save(member);
    }

    // 🔹 Üzvü sil
    @Override
    public void delete(Long id) {
        teamMemberRepository.deleteById(id);
    }
}
