package com.backend134.salon.repositories;

import com.backend134.salon.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findTop6ByFeaturedTrueOrderByIdAsc();
}
