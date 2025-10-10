package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminContactCreateDto;
import com.backend134.salon.admin.dtos.AdminContactResponseDto;
import com.backend134.salon.admin.dtos.AdminContactUpdateDto;

import java.util.List;

public interface AdminContactService {
    List<AdminContactResponseDto> getAll();
    AdminContactResponseDto getById(Long id);
    void create(AdminContactCreateDto dto);
    void update(AdminContactUpdateDto dto);
    void delete(Long id);
}
