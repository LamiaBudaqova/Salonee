package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminStaffCreateDto {
    private String fullName;
    private String phone;
    private String email;
    private String position;
    private String imageUrl;
    private Long branchId;   // filial seçimi
    private Boolean active = true;
    private String username;
    private String password;
}
