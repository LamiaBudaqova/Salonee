package com.backend134.salon.repositories;

import com.backend134.salon.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    // xidmete gore ustaları getirmek
    List<Staff> findByServices_Id(Long serviceId);
}
