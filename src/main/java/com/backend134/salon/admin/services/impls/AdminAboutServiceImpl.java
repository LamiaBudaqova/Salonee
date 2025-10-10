package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminAboutResponseDto;
import com.backend134.salon.admin.dtos.AdminAboutUpdateDto;
import com.backend134.salon.admin.services.AdminAboutService;
import com.backend134.salon.models.About;
import com.backend134.salon.repositories.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAboutServiceImpl implements AdminAboutService {

    private final AboutRepository aboutRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminAboutResponseDto getAbout() {
        About about = aboutRepository.findAll().stream().findFirst().orElse(null);
        return about != null ? modelMapper.map(about, AdminAboutResponseDto.class) : null;
    }

    @Override
    public void updateAbout(Long id, AdminAboutUpdateDto dto) {
        About about = aboutRepository.findById(id).orElseThrow(() -> new RuntimeException("About not found"));
        about.setTitle(dto.getTitle());
        about.setSubtitle(dto.getSubtitle());
        about.setDescription(dto.getDescription());
        about.setImagePath(dto.getImagePath());
        about.setExperienceYears(dto.getExperienceYears());
        about.setClientsCount(dto.getClientsCount());
        about.setPhoneNumber(dto.getPhoneNumber());
        about.setPhoneLabel(dto.getPhoneLabel());
        aboutRepository.save(about);
    }
}

