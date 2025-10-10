package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminFooterUpdateDto {
    private String address;
    private String phone;
    private String email;
    private String facebookUrl;
    private String instagramUrl;
    private String description;
}
