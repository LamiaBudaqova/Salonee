package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.footer.FooterCreateDto;
import com.backend134.salon.dtos.footer.FooterResponseDto;
import com.backend134.salon.dtos.footer.FooterUpdateDto;
import com.backend134.salon.models.Footer;
import com.backend134.salon.repositories.FooterRepository;
import com.backend134.salon.services.FooterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FooterServiceImpl implements FooterService {
    private final FooterRepository footerRepository;
    private final ModelMapper modelMapper;

    @Override
    public FooterResponseDto getFooter() {
        Footer footer = footerRepository.findAll().stream().findFirst().orElse(null);
        return footer != null ? modelMapper.map(footer, FooterResponseDto.class) : null;
    }

    @Override
    public FooterResponseDto createFooter(FooterCreateDto dto) {
        Footer footer = modelMapper.map(dto, Footer.class);
        Footer saved = footerRepository.save(footer);
        return modelMapper.map(saved, FooterResponseDto.class);
    }

    @Override
    public FooterResponseDto updateFooter(Long id, FooterUpdateDto dto) {
        Footer footer = footerRepository.findById(id).orElseThrow(() -> new RuntimeException("Footer not found"));
        footer.setAddress(dto.getAddress());
        footer.setPhone(dto.getPhone());
        footer.setEmail(dto.getEmail());
        footer.setFacebookUrl(dto.getFacebookUrl());
        footer.setInstagramUrl(dto.getInstagramUrl());
        footer.setDescription(dto.getDescription());
        Footer updated = footerRepository.save(footer);
        return modelMapper.map(updated, FooterResponseDto.class);
    }
}
