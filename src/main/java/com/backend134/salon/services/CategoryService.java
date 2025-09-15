package com.backend134.salon.services;

import com.backend134.salon.dtos.category.CategoryHomeFeaturedDto;

import java.util.List;

public interface CategoryService {
    List<CategoryHomeFeaturedDto> getHomeFeaturedCategories();
}
