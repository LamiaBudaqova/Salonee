package com.backend134.salon.repositories;

import com.backend134.salon.models.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryImageRepository extends JpaRepository<GalleryImage, Long> {
    List<GalleryImage> findAllByOrderBySortOrderAsc();
}
