package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminTestimonialCreateDto;
import com.backend134.salon.admin.dtos.AdminTestimonialResponseDto;
import com.backend134.salon.admin.dtos.AdminTestimonialUpdateDto;
import com.backend134.salon.admin.services.AdminTestimonialService;
import com.backend134.salon.models.Testimonial;
import com.backend134.salon.repositories.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminTestimonialServiceImpl implements AdminTestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminTestimonialResponseDto> getAll() {
        return testimonialRepository.findAll()
                .stream()
                .map(testimonial -> modelMapper.map(testimonial, AdminTestimonialResponseDto.class))
                .toList();
    }

    @Override
    public AdminTestimonialResponseDto getById(Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rəy tapılmadı"));
        return modelMapper.map(testimonial, AdminTestimonialResponseDto.class);
    }

    @Override
    public void create(AdminTestimonialCreateDto dto) {
        Testimonial testimonial = modelMapper.map(dto, Testimonial.class);
        testimonialRepository.save(testimonial);
    }

    @Override
    public void update(Long id, AdminTestimonialUpdateDto dto) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rəy tapılmadı"));
        testimonial.setClientName(dto.getClientName());
        testimonial.setProfession(dto.getProfession());
        testimonial.setFeedback(dto.getFeedback());
        testimonialRepository.save(testimonial);
    }

    @Override
    public void delete(Long id) {
        testimonialRepository.deleteById(id);
    }
}
