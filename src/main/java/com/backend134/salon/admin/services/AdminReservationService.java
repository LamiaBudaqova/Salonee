package com.backend134.salon.admin.services;

import com.backend134.salon.admin.dtos.AdminReservationDto;
import com.backend134.salon.admin.dtos.AdminReservationUpdateDto;
import com.backend134.salon.models.Reservation;

import java.util.List;

public interface AdminReservationService {
    List<AdminReservationDto> getAllReservations();
    void updateStatus(AdminReservationUpdateDto dto);
}
