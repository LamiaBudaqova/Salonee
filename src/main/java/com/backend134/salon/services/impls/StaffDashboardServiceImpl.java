package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.staff.StaffDashboardStatsDto;
import com.backend134.salon.dtos.staff.StaffReservationDto;
import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.repositories.ReservationRepository;
import com.backend134.salon.services.StaffDashboardService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffDashboardServiceImpl implements StaffDashboardService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    public StaffDashboardStatsDto getStatsForStaff(Long staffId) {
        long pending = reservationRepository.countByStaff_IdAndStatus(staffId, ReservationStatus.PENDING);
        long approved = reservationRepository.countByStaff_IdAndStatus(staffId, ReservationStatus.APPROVED);
        long done = reservationRepository.countByStaff_IdAndStatus(staffId, ReservationStatus.DONE);

        return new StaffDashboardStatsDto(pending, approved, done);
    }

    @Override
    public List<StaffReservationDto> getReservationsForStaff(Long staffId) {
        return reservationRepository.findByStaff_Id(staffId)
                .stream()
                .map(r -> modelMapper.map(r, StaffReservationDto.class))
                .collect(Collectors.toList());
    }
}