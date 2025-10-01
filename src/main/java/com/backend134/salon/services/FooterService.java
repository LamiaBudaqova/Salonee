package com.backend134.salon.services;

import com.backend134.salon.dtos.footer.FooterCreateDto;
import com.backend134.salon.dtos.footer.FooterResponseDto;
import com.backend134.salon.dtos.footer.FooterUpdateDto;

public interface FooterService {
    FooterResponseDto getFooter();
    FooterResponseDto createFooter(FooterCreateDto dto);
    FooterResponseDto updateFooter(Long id, FooterUpdateDto dto);
}
