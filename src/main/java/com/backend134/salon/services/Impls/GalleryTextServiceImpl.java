package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.gallery.GalleryTextDto;
import com.backend134.salon.models.GalleryText;
import com.backend134.salon.repositories.GalleryTextRepository;
import com.backend134.salon.services.GalleryTextService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GalleryTextServiceImpl implements GalleryTextService {

    private final GalleryTextRepository galleryTextRepository;
    private final ModelMapper modelMapper;


    @Override
    public GalleryTextDto getText() {
        GalleryText entity = galleryTextRepository.findById(1L).orElse(null);
        return entity != null ? modelMapper.map(entity, GalleryTextDto.class) : null;
    }
}