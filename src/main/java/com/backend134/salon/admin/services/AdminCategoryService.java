package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminCategoryCreateDto;
import com.backend134.salon.admin.dtos.AdminCategoryResponseDto;
import com.backend134.salon.admin.dtos.AdminCategoryUpdateDto;

import java.util.List;

public interface AdminCategoryService {
    List<AdminCategoryResponseDto> getAll();
    AdminCategoryResponseDto getById(Long id);
    void create(AdminCategoryCreateDto dto);
    void update(AdminCategoryUpdateDto dto);
    void delete(Long id);
}
