package com.backend134.salon.services;

import com.backend134.salon.dtos.salonservice.SalonServiceDto;
import com.backend134.salon.models.SalonService;

import java.util.List;

public interface SalonServiceService {
    List<SalonServiceDto> getServicesByCategoryId(Long categoryId);
    SalonServiceDto getServiceById(Long id);
    List<SalonService> getAll();
    List<SalonServiceDto> getByBranch(Long branchId);
    List<SalonServiceDto> getByStaff(Long staffId);

}