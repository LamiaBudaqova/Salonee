package com.backend134.salon.services;

import com.backend134.salon.dtos.staff.StaffSimpleDto;

import java.util.List;

public interface StaffService {
    List<StaffSimpleDto> getAll();
    List<StaffSimpleDto> getByServiceId(Long serviceId);
    List<StaffSimpleDto> getByBranch(Long branchId);
    List<StaffSimpleDto> getByBranchAndService(Long branchId, Long serviceId);

}

