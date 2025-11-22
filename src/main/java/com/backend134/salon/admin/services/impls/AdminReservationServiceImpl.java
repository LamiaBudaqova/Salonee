package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminReservationDto;
import com.backend134.salon.admin.dtos.AdminReservationUpdateDto;
import com.backend134.salon.admin.services.AdminReservationService;
import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.models.Reservation;
import com.backend134.salon.repositories.ReservationRepository;
import com.backend134.salon.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminReservationServiceImpl implements AdminReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @Override
    public List<AdminReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(r -> {
                    AdminReservationDto dto = new AdminReservationDto();
                    dto.setId(r.getId());
                    dto.setServiceName(r.getService() != null ? r.getService().getName() : "-");
                    dto.setStaffName(r.getStaff() != null ? r.getStaff().getFullName() : "-");
                    dto.setDate(r.getDate());
                    dto.setStartTime(r.getStartTime());
                    dto.setEndTime(r.getEndTime());
                    dto.setCustomerName(r.getCustomerName());
                    dto.setCustomerPhone(r.getCustomerPhone());
                    dto.setStatus(r.getStatus());
                    dto.setNotes(r.getNotes());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateStatus(AdminReservationUpdateDto dto) {

        // dbdan rezervi tapırıq
        Reservation reservation = reservationRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Rezerv tapılmadı"));

        // APPROVE - Business logic + Telegram + cashback
        if (dto.getStatus() == ReservationStatus.APPROVED) {
            reservationService.approveReservation(dto.getId());
            return;
        }

        // REJECT - Business logic + Telegram
        if (dto.getStatus() == ReservationStatus.REJECTED) {
            reservationService.rejectReservation(dto.getId());
            return;
        }

        // eks halda sadece status deyiş
        reservation.setStatus(dto.getStatus());
        reservation.setNotes(dto.getNotes());
        reservationRepository.save(reservation);
    }
}
