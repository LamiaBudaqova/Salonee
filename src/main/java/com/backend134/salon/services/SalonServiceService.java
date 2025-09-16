package com.backend134.salon.services;

import com.backend134.salon.models.SalonService;

import java.util.List;

public interface SalonServiceService {
    List<SalonService> getAllServices();
    List<SalonService> getServicesByCategoryId(Long categoryId);
}
