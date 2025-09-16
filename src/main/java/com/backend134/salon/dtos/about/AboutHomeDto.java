package com.backend134.salon.dtos.about;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AboutHomeDto {
    private Long id;
    private String title;
    private String subtitle;
    private String description;
    private String imagePath;
}
