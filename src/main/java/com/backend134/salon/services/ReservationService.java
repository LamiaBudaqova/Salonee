package com.backend134.salon.services;

import com.backend134.salon.dtos.reservation.ReservationCreateDto;

public interface ReservationService {
    Long create(ReservationCreateDto dto);
    void approveReservation(Long id);
    void rejectReservation(Long id);
}
