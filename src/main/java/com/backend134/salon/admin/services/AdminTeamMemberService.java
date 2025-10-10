package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminTeamMemberCreateDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberResponseDto;
import com.backend134.salon.admin.dtos.AdminTeamMemberUpdateDto;

import java.util.List;

public interface AdminTeamMemberService {
    List<AdminTeamMemberResponseDto> getAll();
    AdminTeamMemberResponseDto getById(Long id);
    void create(AdminTeamMemberCreateDto dto);
    void update(Long id, AdminTeamMemberUpdateDto dto);
    void delete(Long id);
}
