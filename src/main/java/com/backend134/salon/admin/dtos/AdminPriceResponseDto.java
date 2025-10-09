package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminPriceResponseDto {
    private Long id;
    private String serviceName;
    private Double amount;
}
