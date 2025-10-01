package com.backend134.salon.dtos.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDashboardDto {
    private Long id;
    private String address;
    private boolean active;
}
