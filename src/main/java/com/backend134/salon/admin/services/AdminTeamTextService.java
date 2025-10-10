package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminTeamTextResponseDto;
import com.backend134.salon.admin.dtos.AdminTeamTextUpdateDto;

public interface AdminTeamTextService {
    AdminTeamTextResponseDto getTeamText();
    void updateTeamText(Long id, AdminTeamTextUpdateDto dto);
}
