package com.backend134.salon.services;

import com.backend134.salon.dtos.gallery.GalleryImageDto;
import com.backend134.salon.models.GalleryImage;

import java.util.List;

public interface GalleryImageService {
    List<GalleryImageDto> getAllImages();
}
