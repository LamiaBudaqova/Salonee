package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.staff.StaffDashboardStatsDto;
import com.backend134.salon.dtos.staff.StaffReservationDto;
import com.backend134.salon.dtos.staff.StaffProfileDto;
import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.models.Staff;
import com.backend134.salon.repositories.ReservationRepository;
import com.backend134.salon.repositories.StaffRepository;
import com.backend134.salon.services.StaffDashboardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
 public class StaffDashboardServiceImpl implements StaffDashboardService {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;
    private final StaffRepository staffRepository;

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

    @Override
    @Transactional
    public void updateReservationStatus(Long reservationId, ReservationStatus status) {
        reservationRepository.updateStatus(reservationId, status);
    }

    @Override
    public Optional<StaffProfileDto> getProfileByUsername(String username) {
        return staffRepository.findByEmail(username)
                .map(staff -> {
                    StaffProfileDto dto = new StaffProfileDto();
                    dto.setId(staff.getId()); // 🔹 Əlavə et
                    dto.setFullName(staff.getFullName());
                    dto.setPhone(staff.getPhone());
                    dto.setEmail(staff.getEmail());
                    dto.setPosition(staff.getPosition());
                    dto.setImageUrl(staff.getImageUrl());
                    if (staff.getBranch() != null)
                        dto.setBranchName(staff.getBranch().getName());
                    return dto;
                });
    }


    @Override
    public StaffProfileDto getStaffProfile(String username) {
        var staff = staffRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usta tapılmadı"));

        // ✅ ModelMapper əvəzinə manual mapping, çünki branch obyektidi
        StaffProfileDto dto = new StaffProfileDto();
        dto.setId(staff.getId());
        dto.setFullName(staff.getFullName());
        dto.setPhone(staff.getPhone());
        dto.setEmail(staff.getEmail());
        dto.setPosition(staff.getPosition());
        dto.setImageUrl(staff.getImageUrl() != null ? staff.getImageUrl() : "/front/img/default-user.png");
        dto.setBranchName(staff.getBranch() != null ? staff.getBranch().getName() : "-");

        return dto;
    }

}
