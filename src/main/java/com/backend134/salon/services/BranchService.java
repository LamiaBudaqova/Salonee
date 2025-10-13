package com.backend134.salon.services;

import com.backend134.salon.admin.dtos.AdminBranchCreateDto;
import com.backend134.salon.admin.dtos.AdminBranchUpdateDto;
import com.backend134.salon.dtos.branch.BranchDto;

import java.util.List;

public interface BranchService {
    List<BranchDto> getAllBranches();
    void create(AdminBranchCreateDto dto);
    void update(Long id, AdminBranchUpdateDto dto);
    void delete(Long id);
}
