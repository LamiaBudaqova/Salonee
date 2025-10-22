package com.backend134.salon.staff.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffProfileUpdateDto {
    private String fullName;
    private String phone;
    private String position;
    private Long branchId;
    private MultipartFile image; // şəkil yükləmək üçün
}
