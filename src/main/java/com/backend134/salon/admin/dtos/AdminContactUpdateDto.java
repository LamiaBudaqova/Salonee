package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminContactUpdateDto {
    private Long id;
    private String address;
    private String phone;
    private String email;
    private String mapLink;
    private boolean active;
}
