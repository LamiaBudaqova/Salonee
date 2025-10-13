package com.backend134.salon.repositories;

import com.backend134.salon.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
           select r from Reservation r
           where r.date = :date
             and (:staffId is null or r.staff.id = :staffId)
             and r.status in ('PENDING','APPROVED')
             and (r.startTime < :endTime and r.endTime > :startTime)
           """)
    List<Reservation> findConflicts(@Param("date") LocalDate date,
                                    @Param("startTime") LocalTime startTime,
                                    @Param("endTime") LocalTime endTime,
                                    @Param("staffId") Long staffId);
}
