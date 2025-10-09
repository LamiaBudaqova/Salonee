package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.gallery.GalleryImageDto;
import com.backend134.salon.models.GalleryImage;
import com.backend134.salon.repositories.GalleryImageRepository;
import com.backend134.salon.services.GalleryImageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<GalleryImageDto> getLimitedImages(int limit) {
        return galleryImageRepository.findAll()
                .stream()
                .limit(limit) // yalnız limit qədər
                .map(img -> modelMapper.map(img, GalleryImageDto.class))
                .collect(Collectors.toList());
    }
}
