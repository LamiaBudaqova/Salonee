package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminAboutResponseDto;
import com.backend134.salon.admin.dtos.AdminAboutUpdateDto;

public interface AdminAboutService {
    AdminAboutResponseDto getAbout();
    void updateAbout(Long id, AdminAboutUpdateDto dto);
}
