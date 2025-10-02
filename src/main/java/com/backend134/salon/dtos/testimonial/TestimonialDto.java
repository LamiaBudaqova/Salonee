package com.backend134.salon.dtos.testimonial;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDto {
    private String feedback;
    private String clientName;
    private String profession;
    private String imageUrl;
}
