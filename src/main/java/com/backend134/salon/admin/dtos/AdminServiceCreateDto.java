package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminServiceCreateDto {
    private String name;
    private String description;
    private String image;
    private String extraInfo;
    private Long categoryId;
}
