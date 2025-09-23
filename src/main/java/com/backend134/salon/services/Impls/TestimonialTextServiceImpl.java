package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.team.TeamTextDto;
import com.backend134.salon.dtos.testimonial.TestimonialDto;
import com.backend134.salon.dtos.testimonial.TestimonialTextDto;
import com.backend134.salon.models.TestimonialText;
import com.backend134.salon.repositories.TestimonialRepository;
import com.backend134.salon.repositories.TestimonialTextRepository;
import com.backend134.salon.services.TestimonialTextService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonialTextServiceImpl implements TestimonialTextService {

    private final TestimonialTextRepository testimonialTextRepository;
    private final ModelMapper modelMapper;


    @Override
    public TestimonialTextDto getText() {
        TestimonialText text = testimonialTextRepository.findById(1L).orElse(null);
        return modelMapper.map(text, TestimonialTextDto.class);
    }
}

