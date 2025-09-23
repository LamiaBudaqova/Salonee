package com.backend134.salon.dtos.gallery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GalleryImageDto {
    private Long id;
    private String imagePath;
    private Integer sortOrder;
    private String columnSize;
}
