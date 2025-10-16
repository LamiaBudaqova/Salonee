package com.backend134.salon.repositories;

import com.backend134.salon.dtos.staff.StaffSimpleDto;
import com.backend134.salon.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    List<Staff> findByServices_Id(Long serviceId);
    Optional<Staff> findByEmail(String email);
}