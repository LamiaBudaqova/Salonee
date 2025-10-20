package com.backend134.salon.repositories;

import com.backend134.salon.models.SalonService;
import com.backend134.salon.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByUsername(String username);
    Optional<Staff> findByEmail(String email);
    List<Staff> findByServices_Id(Long serviceId);
    List<Staff> findByBranch_Id(Long branchId);
    List<Staff> findByBranch_IdAndServices_Id(Long branchId, Long serviceId);

    //  Filiala görə xidmətlər
    @Query("""
            SELECT DISTINCT srv
            FROM Staff s
            JOIN s.services srv
            WHERE s.branch.id = :branchId
            """)
    List<SalonService> findServicesByBranchId(@Param("branchId") Long branchId);

    //  Ustaya görə xidmətlər
    @Query("""
            SELECT DISTINCT srv
            FROM Staff s
            JOIN s.services srv
            WHERE s.id = :staffId
            """)
    List<SalonService> findServicesByStaffId(@Param("staffId") Long staffId);
}