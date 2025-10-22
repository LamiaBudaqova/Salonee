package com.backend134.salon.staff.dtos;

import com.backend134.salon.dtos.branch.BranchDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffProfileDto {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String position;
    private BranchDto branch;
    private String branchName;
    private String imageUrl;
}
