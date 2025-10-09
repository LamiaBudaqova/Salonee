package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminGalleryImageCreateDto;
import com.backend134.salon.admin.dtos.AdminGalleryImageResponseDto;
import com.backend134.salon.admin.dtos.AdminGalleryImageUpdateDto;

import java.util.List;

public interface AdminGalleryImageService {
    List<AdminGalleryImageResponseDto> getAll();
    AdminGalleryImageResponseDto getById(Long id);
    void create(AdminGalleryImageCreateDto dto);
    void update(Long id, AdminGalleryImageUpdateDto dto);
    void delete(Long id);
}
