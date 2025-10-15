package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.reservation.ReservationCreateDto;
import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.models.*;
import com.backend134.salon.repositories.*;
import com.backend134.salon.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final StaffRepository staffRepository;

    @Override
    public Long create(ReservationCreateDto dto) {
        var service = salonServiceRepository.findById(dto.getServiceId()).orElseThrow();

        Staff staff = null;
        if (dto.getStaffId() != null) {
            staff = staffRepository.findById(dto.getStaffId()).orElseThrow();
        }

        LocalTime end = dto.getStartTime().plusMinutes(30);

        var conflicts = reservationRepository.findConflicts(
                dto.getDate(), dto.getStartTime(), end, staff != null ? staff.getId() : null
        );

        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("Bu vaxt artıq doludur!");
        }

        var r = new Reservation();
        r.setService(service);
        r.setStaff(staff);
        r.setDate(dto.getDate());
        r.setStartTime(dto.getStartTime());
        r.setEndTime(end);
        r.setStatus(ReservationStatus.PENDING);
        r.setCustomerName(dto.getCustomerName());
        r.setCustomerPhone(dto.getCustomerPhone());
        r.setNotes(dto.getNotes());

        reservationRepository.save(r);
        return r.getId();
    }
}
