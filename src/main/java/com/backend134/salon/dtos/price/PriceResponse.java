package com.backend134.salon.dtos.price;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceResponse {
    private Long id;
    private String serviceName;
    private Double amount;
}
