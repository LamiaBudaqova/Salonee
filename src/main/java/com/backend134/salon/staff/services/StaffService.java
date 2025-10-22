package com.backend134.salon.staff.services;

import com.backend134.salon.staff.dtos.StaffSimpleDto;

import java.util.List;

public interface StaffService {
    List<StaffSimpleDto> getAll();
    List<StaffSimpleDto> getByServiceId(Long serviceId);
    List<StaffSimpleDto> getByBranch(Long branchId);
    List<StaffSimpleDto> getByBranchAndService(Long branchId, Long serviceId);

}

