package com.backend134.salon.services;

import com.backend134.salon.dtos.testimonial.TestimonialCreateDto;
import com.backend134.salon.dtos.testimonial.TestimonialDto;

import java.util.List;

public interface TestimonialService {
    List<TestimonialDto> getAllTestimonials();
    void createTestimonial(TestimonialCreateDto dto);
}
