package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminGalleryImageResponseDto {
    private Long id;
    private String imagePath;
    private Integer sortOrder;
    private String columnSize;
}
