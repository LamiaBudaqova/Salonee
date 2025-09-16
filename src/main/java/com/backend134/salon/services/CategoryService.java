package com.backend134.salon.services;

import com.backend134.salon.dtos.category.CategoryHomeFeaturedDto;
import com.backend134.salon.models.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryHomeFeaturedDto> getHomeFeaturedCategories();
    List<Category> getAllCategories();
    Category getCategoryById(Long id);

}
