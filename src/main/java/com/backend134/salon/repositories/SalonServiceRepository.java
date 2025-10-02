package com.backend134.salon.repositories;

import com.backend134.salon.models.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalonServiceRepository extends JpaRepository<SalonService, Long> {
    List<SalonService> findByCategoryId(Long categoryId);
}