package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminBlogResponseDto {
    private Long id;
    private String title;
    private String content;
    private String imagePath;
    private LocalDate createdAt;
}
