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
    private String text;
    private String name;
    private String profession;
    private String imageUrl;
}
