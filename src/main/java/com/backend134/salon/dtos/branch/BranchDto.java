package com.backend134.salon.dtos.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDto {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
