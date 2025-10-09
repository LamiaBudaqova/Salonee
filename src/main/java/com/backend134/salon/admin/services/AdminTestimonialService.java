package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminTestimonialCreateDto;
import com.backend134.salon.admin.dtos.AdminTestimonialResponseDto;
import com.backend134.salon.admin.dtos.AdminTestimonialUpdateDto;

import java.util.List;

public interface AdminTestimonialService {
    List<AdminTestimonialResponseDto> getAll();
    AdminTestimonialResponseDto getById(Long id);
    void create(AdminTestimonialCreateDto dto);
    void update(Long id, AdminTestimonialUpdateDto dto);
    void delete(Long id);
}
