package com.backend134.salon.dtos.salonservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalonServiceDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String categoryName;
}