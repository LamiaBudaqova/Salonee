package com.backend134.salon.dtos.staff;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffSimpleDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
}
