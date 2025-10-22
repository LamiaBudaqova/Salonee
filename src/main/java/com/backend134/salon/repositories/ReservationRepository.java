package com.backend134.salon.repositories;

import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 🔹 Ustanın rezervlərini tapmaq
    List<Reservation> findByStaff_Id(Long staffId);

    // 🔹 Statusa görə say
    long countByStaff_IdAndStatus(Long staffId, ReservationStatus status);

    // 🔹 Vaxt toqquşmalarını tapmaq (Booking zamanı)
    @Query("""
        SELECT r FROM Reservation r
        WHERE r.date = :date
          AND (:staffId IS NULL OR r.staff.id = :staffId)
          AND r.status IN ('PENDING','APPROVED')
          AND (r.startTime < :endTime AND r.endTime > :startTime)
    """)
    List<Reservation> findConflicts(@Param("date") LocalDate date,
                                    @Param("startTime") LocalTime startTime,
                                    @Param("endTime") LocalTime endTime,
                                    @Param("staffId") Long staffId);

    @Modifying
    @Query("UPDATE Reservation r SET r.status = :status WHERE r.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") ReservationStatus status);

    List<Reservation> findByStaff_IdAndDateBetween(Long staffId, LocalDate start, LocalDate end);
    List<Reservation> findByStaff_IdAndStatus(Long staffId, ReservationStatus status);
    List<Reservation> findByStaff_IdAndStatusAndDateBetween(Long staffId, ReservationStatus status, LocalDate start, LocalDate end);

}