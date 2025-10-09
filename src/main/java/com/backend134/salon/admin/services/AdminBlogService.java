package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminBlogCreateDto;
import com.backend134.salon.admin.dtos.AdminBlogResponseDto;
import com.backend134.salon.admin.dtos.AdminBlogUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminBlogService {
    List<AdminBlogResponseDto> getAll();
    AdminBlogResponseDto getById(Long id);
    void create(AdminBlogCreateDto dto);
    void update(Long id, AdminBlogUpdateDto dto);
    void delete(Long id);
}
