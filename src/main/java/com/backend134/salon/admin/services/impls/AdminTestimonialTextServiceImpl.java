package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminTestimonialTextUpdateDto;
import com.backend134.salon.admin.services.AdminTestimonialTextService;
import com.backend134.salon.models.TestimonialText;
import com.backend134.salon.repositories.TestimonialTextRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminTestimonialTextServiceImpl implements AdminTestimonialTextService {
    private final TestimonialTextRepository testimonialTextRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminTestimonialTextUpdateDto getText() {
        TestimonialText text = testimonialTextRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("TestimonialText tapılmadı"));
        return modelMapper.map(text, AdminTestimonialTextUpdateDto.class);
    }

    @Override
    public void update(AdminTestimonialTextUpdateDto dto) {
        TestimonialText text = testimonialTextRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("TestimonialText tapılmadı"));
        text.setTitle(dto.getTitle());
        text.setSubtitle(dto.getSubtitle());
        testimonialTextRepository.save(text);
    }
}