package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.gallery.GalleryImageDto;
import com.backend134.salon.models.GalleryImage;
import com.backend134.salon.repositories.GalleryImageRepository;
import com.backend134.salon.services.GalleryImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryImageServiceImpl implements GalleryImageService {

    private final GalleryImageRepository galleryImageRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GalleryImageDto> getAllImages() {
        return galleryImageRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(image -> modelMapper.map(image, GalleryImageDto.class))
                .toList();
    }
}
