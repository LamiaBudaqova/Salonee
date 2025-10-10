package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminAboutUpdateDto {
    private String title;
    private String subtitle;
    private String description;
    private String imagePath;
    private Integer experienceYears;
    private Integer clientsCount;
    private String phoneNumber;
    private String phoneLabel;
}
