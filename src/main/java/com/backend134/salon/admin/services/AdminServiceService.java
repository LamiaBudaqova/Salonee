package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminServiceCreateDto;
import com.backend134.salon.admin.dtos.AdminServiceResponseDto;
import com.backend134.salon.admin.dtos.AdminServiceUpdateDto;

import java.util.List;

public interface AdminServiceService {
    List<AdminServiceResponseDto> getAll();
    AdminServiceResponseDto getById(Long id);
    void create(AdminServiceCreateDto dto);
    void update(AdminServiceUpdateDto dto);
    void delete(Long id);
}
