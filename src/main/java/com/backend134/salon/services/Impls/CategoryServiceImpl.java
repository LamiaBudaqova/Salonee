package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.category.CategoryHomeFeaturedDto;
import com.backend134.salon.models.Category;
import com.backend134.salon.repositories.CategoryRepository;
import com.backend134.salon.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryHomeFeaturedDto> getHomeFeaturedCategories(){
        List<Category> categories = categoryRepository.findTop6ByFeaturedTrueOrderByIdAsc();
        List<CategoryHomeFeaturedDto> categoryHomeFeaturedDtoList = categories.stream()
                .map(category -> new CategoryHomeFeaturedDto(
                        category.getId(),
                        category.getName(),
                        category.getIcon(),
                        category.getServices().size()
                ))
                .toList();
        return categoryHomeFeaturedDtoList;
    }

}
