package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminReservationUpdateDto;
import com.backend134.salon.admin.services.AdminReservationService;
import com.backend134.salon.models.Reservation;
import com.backend134.salon.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminReservationServiceImpl implements AdminReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public void updateStatus(AdminReservationUpdateDto dto) {
        Reservation reservation = reservationRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Rezerv tapılmadı"));
        reservation.setStatus(dto.getStatus());
        reservation.setNotes(dto.getNotes());
    }
}
