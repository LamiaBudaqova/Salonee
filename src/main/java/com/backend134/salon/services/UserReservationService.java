package com.backend134.salon.services;

import com.backend134.salon.dtos.reservation.ReservationDto;

import java.util.List;

public interface UserReservationService {
    List<ReservationDto> getUserReservations(Long userId);
}
