package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminStaffCreateDto;
import com.backend134.salon.admin.dtos.AdminStaffDto;
import com.backend134.salon.admin.dtos.AdminStaffUpdateDto;

import java.util.List;

public interface AdminStaffService {
    List<AdminStaffDto> getAll();
    AdminStaffUpdateDto getById(Long id);
    void create(AdminStaffCreateDto dto);
    void update(AdminStaffUpdateDto dto);
    void delete(Long id);
}
