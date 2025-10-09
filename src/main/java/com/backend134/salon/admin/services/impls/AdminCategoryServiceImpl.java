package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminCategoryCreateDto;
import com.backend134.salon.admin.dtos.AdminCategoryResponseDto;
import com.backend134.salon.admin.dtos.AdminCategoryUpdateDto;
import com.backend134.salon.admin.services.AdminCategoryService;
import com.backend134.salon.models.Category;
import com.backend134.salon.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminCategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> modelMapper.map(c, AdminCategoryResponseDto.class))
                .toList();
    }

    @Override
    public AdminCategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
        return modelMapper.map(category, AdminCategoryResponseDto.class);
    }

    @Override
    public void create(AdminCategoryCreateDto dto) {
        Category category = modelMapper.map(dto, Category.class);
        categoryRepository.save(category);
    }

    @Override
    public void update(AdminCategoryUpdateDto dto) {
        Category category = categoryRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));

        category.setName(dto.getName());
        category.setIcon(dto.getIcon());
        category.setFeatured(dto.isFeatured());

        categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}