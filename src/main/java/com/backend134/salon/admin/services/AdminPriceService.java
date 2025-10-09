package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminPriceCreateDto;
import com.backend134.salon.admin.dtos.AdminPriceResponseDto;
import com.backend134.salon.admin.dtos.AdminPriceUpdateDto;

import java.util.List;

public interface AdminPriceService {
    List<AdminPriceResponseDto> getAll();
    AdminPriceResponseDto getById(Long id);
    void create(AdminPriceCreateDto dto);
    void update(Long id, AdminPriceUpdateDto dto);
    void delete(Long id);
}
