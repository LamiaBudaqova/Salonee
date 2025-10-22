package com.backend134.salon.staff.services;

import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.staff.dtos.*;

import java.util.List;
import java.util.Optional;

public interface StaffDashboardService {
    StaffDashboardStatsDto getStatsForStaff(Long staffId);
    List<StaffReservationDto> getReservationsForStaff(Long staffId);
    void updateReservationStatus(Long reservationId, ReservationStatus status);
    Optional<StaffProfileDto> getProfileByUsername(String username);
    StaffProfileDto getStaffProfile(String username);
    void updateProfile(String username, StaffProfileUpdateDto dto);
    List<StaffReservationDto> getFilteredReservations(Long staffId, StaffReservationFilterDto filter);

}
