package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminGalleryImageCreateDto;
import com.backend134.salon.admin.dtos.AdminGalleryImageResponseDto;
import com.backend134.salon.admin.dtos.AdminGalleryImageUpdateDto;
import com.backend134.salon.admin.services.AdminGalleryImageService;
import com.backend134.salon.models.GalleryImage;
import com.backend134.salon.repositories.GalleryImageRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminGalleryImageServiceImpl implements AdminGalleryImageService {

    private final GalleryImageRepository galleryImageRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminGalleryImageResponseDto> getAll() {
        return galleryImageRepository.findAllByOrderBySortOrderAsc()
                .stream()
                .map(img -> modelMapper.map(img, AdminGalleryImageResponseDto.class))
                .toList();
    }

    @Override
    public AdminGalleryImageResponseDto getById(Long id) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şəkil tapılmadı"));
        return modelMapper.map(image, AdminGalleryImageResponseDto.class);
    }

    @Override
    public void create(AdminGalleryImageCreateDto dto) {
        GalleryImage image = modelMapper.map(dto, GalleryImage.class);
        galleryImageRepository.save(image);
    }

    @Override
    public void update(Long id, AdminGalleryImageUpdateDto dto) {
        GalleryImage image = galleryImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Şəkil tapılmadı"));

        image.setImagePath(dto.getImagePath());
        image.setSortOrder(dto.getSortOrder());
        image.setColumnSize(dto.getColumnSize());

        galleryImageRepository.save(image);
    }

    @Override
    public void delete(Long id) {
        galleryImageRepository.deleteById(id);
    }
}
