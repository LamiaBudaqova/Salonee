package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminFooterCreateDto;
import com.backend134.salon.admin.dtos.AdminFooterResponseDto;
import com.backend134.salon.admin.dtos.AdminFooterUpdateDto;

public interface AdminFooterService {
    AdminFooterResponseDto getFooter();
    AdminFooterResponseDto createFooter(AdminFooterCreateDto dto);
    AdminFooterResponseDto updateFooter(Long id, AdminFooterUpdateDto dto);
}
