package com.backend134.salon.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminTeamMemberUpdateDto {
    private String name;
    private String position;
    private String imageUrl;
    private String facebook;
    private String instagram;
    private Long branchId;
}
