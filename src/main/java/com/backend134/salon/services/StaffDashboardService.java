package com.backend134.salon.services;

import com.backend134.salon.dtos.staff.StaffDashboardStatsDto;
import com.backend134.salon.dtos.staff.StaffReservationDto;
import com.backend134.salon.enums.ReservationStatus;

import java.util.List;

public interface StaffDashboardService {
    StaffDashboardStatsDto getStatsForStaff(Long staffId);
    List<StaffReservationDto> getReservationsForStaff(Long staffId);
    void updateReservationStatus(Long reservationId, ReservationStatus status);

}