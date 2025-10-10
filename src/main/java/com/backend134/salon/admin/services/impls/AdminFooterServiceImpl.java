package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminFooterCreateDto;
import com.backend134.salon.admin.dtos.AdminFooterResponseDto;
import com.backend134.salon.admin.dtos.AdminFooterUpdateDto;
import com.backend134.salon.admin.services.AdminFooterService;
import com.backend134.salon.models.Footer;
import com.backend134.salon.repositories.FooterRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminFooterServiceImpl implements AdminFooterService {

    private final FooterRepository footerRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminFooterResponseDto getFooter() {
        Footer footer = footerRepository.findAll().stream().findFirst().orElse(null);
        return footer != null ? modelMapper.map(footer, AdminFooterResponseDto.class) : null;
    }

    @Override
    public AdminFooterResponseDto createFooter(AdminFooterCreateDto dto) {
        Footer footer = modelMapper.map(dto, Footer.class);
        Footer saved = footerRepository.save(footer);
        return modelMapper.map(saved, AdminFooterResponseDto.class);
    }

    @Override
    public AdminFooterResponseDto updateFooter(Long id, AdminFooterUpdateDto dto) {
        Footer footer = footerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Footer tapılmadı"));

        footer.setAddress(dto.getAddress());
        footer.setPhone(dto.getPhone());
        footer.setEmail(dto.getEmail());
        footer.setFacebookUrl(dto.getFacebookUrl());
        footer.setInstagramUrl(dto.getInstagramUrl());
        footer.setDescription(dto.getDescription());

        Footer updated = footerRepository.save(footer);
        return modelMapper.map(updated, AdminFooterResponseDto.class);
    }
}