package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.testimonial.TestimonialDto;
import com.backend134.salon.repositories.TestimonialRepository;
import com.backend134.salon.services.TeamMemberService;
import com.backend134.salon.services.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TestimonialDto> getAllTestimonials() {
        return testimonialRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, TestimonialDto.class))
                .toList();

    }
}
