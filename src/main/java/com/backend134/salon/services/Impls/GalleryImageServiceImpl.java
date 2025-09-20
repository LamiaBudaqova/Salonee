package com.backend134.salon.services.Impls;

import com.backend134.salon.models.GalleryImage;
import com.backend134.salon.repositories.GalleryImageRepository;
import com.backend134.salon.services.GalleryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryImageServiceImpl implements GalleryImageService {

    private final GalleryImageRepository galleryImageRepository;

    @Override
    public List<GalleryImage> getAllImages() {
        return galleryImageRepository.findAllByOrderBySortOrderAsc();
    }
}
