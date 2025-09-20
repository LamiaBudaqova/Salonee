package com.backend134.salon.dtos.hero;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeroDto {
    private Long id;

    private String welcomeText;
    private String title;
    private String callLabel;
    private String callNumber;
    private String mailLabel;
    private String mailAddress;

    private List<HeroImageDto> images;
}
