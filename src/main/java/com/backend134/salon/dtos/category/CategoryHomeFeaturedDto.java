package com.backend134.salon.dtos.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryHomeFeaturedDto {
    private Long id;
    private String name;
    private String icon;
    private Integer serviceCount;
}
