package com.backend134.salon.staff.services.impls;

import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.models.Branch;
import com.backend134.salon.models.Reservation;
import com.backend134.salon.models.Staff;
import com.backend134.salon.repositories.ReservationRepository;
import com.backend134.salon.staff.repositories.StaffRepository;
import com.backend134.salon.staff.services.StaffDashboardService;
import com.backend134.salon.services.TelegramNotificationService;
import com.backend134.salon.staff.dtos.*;
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
    private final TelegramNotificationService telegramNotificationService;


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
        var reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Rezerv tapılmadı"));

        reservation.setStatus(status);
        reservationRepository.save(reservation);

        // 🔹 Telegram mesajı yalnız status dəyişəndə göndərilir
        String message = null;

        if (status == ReservationStatus.APPROVED) {
            message = """
                ✅ Hörmətli %s,
                Sizin rezervasiyanız təsdiqləndi!
                📅 Tarix: %s %s
                💇‍♀️ Usta: %s
                💅 Xidmət: %s
                Sizi salonumuzda gözləyirik 🌸
                """.formatted(
                    reservation.getCustomerName(),
                    reservation.getDate(),
                    reservation.getStartTime(),
                    reservation.getStaff().getFullName(),
                    reservation.getService().getName()
            );
        } else if (status == ReservationStatus.REJECTED) {
            message = """
                ❌ Hörmətli %s,
                Təəssüf ki, rezervasiyanız təsdiqlənmədi.
                Əlavə məlumat üçün bizimlə əlaqə saxlayın 📞
                """.formatted(reservation.getCustomerName());
        }

        // 🔹 Əgər mesaj varsa, Telegrama göndər
        if (message != null) {
            telegramNotificationService.sendTelegramMessage(
                    reservation.getCustomerPhone(),
                    message
            );
        }
    }


    @Override
    public Optional<StaffProfileDto> getProfileByUsername(String username) {
        // Əvvəl email ilə axtar
        Optional<Staff> staffOpt = staffRepository.findByEmail(username);

        // Əgər email-lə tapılmadısa, username ilə axtar
        if (staffOpt.isEmpty()) {
            staffOpt = staffRepository.findByUsername(username);
        }

        // Əgər tapdısa DTO-ya çevir
        return staffOpt.map(staff -> {
            StaffProfileDto dto = new StaffProfileDto();
            dto.setId(staff.getId());
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

    @Override
    @Transactional
    public void updateProfile(String username, StaffProfileUpdateDto dto) {
        Staff staff = staffRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usta tapılmadı"));

        staff.setFullName(dto.getFullName());
        staff.setPhone(dto.getPhone());
        staff.setPosition(dto.getPosition());

        // filial dəyişibsə
        if (dto.getBranchId() != null) {
            staff.setBranch(new Branch());
            staff.getBranch().setId(dto.getBranchId());
        }

        // şəkil varsa - sadə upload nümunəsi
        if (dto.getImage() != null && !dto.getImage().isEmpty()) {
            try {
                String fileName = System.currentTimeMillis() + "_" + dto.getImage().getOriginalFilename();
                String uploadDir = "uploads/staff/";

                java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);
                if (!java.nio.file.Files.exists(uploadPath)) {
                    java.nio.file.Files.createDirectories(uploadPath);
                }

                java.nio.file.Path filePath = uploadPath.resolve(fileName);
                dto.getImage().transferTo(filePath.toFile());
                staff.setImageUrl("/" + uploadDir + fileName);
            } catch (Exception e) {
                throw new RuntimeException("Şəkil yüklənərkən xəta baş verdi: " + e.getMessage());
            }
        }

        staffRepository.save(staff);
    }

    @Override
    public List<StaffReservationDto> getFilteredReservations(Long staffId, StaffReservationFilterDto filter) {
        List<Reservation> list;

        boolean hasDateRange = filter.getStartDate() != null && filter.getEndDate() != null;
        boolean hasStatus = filter.getStatus() != null;

        if (hasStatus && hasDateRange) {
            list = reservationRepository.findByStaff_IdAndStatusAndDateBetween(
                    staffId, filter.getStatus(), filter.getStartDate(), filter.getEndDate());
        } else if (hasStatus) {
            list = reservationRepository.findByStaff_IdAndStatus(staffId, filter.getStatus());
        } else if (hasDateRange) {
            list = reservationRepository.findByStaff_IdAndDateBetween(staffId, filter.getStartDate(), filter.getEndDate());
        } else {
            list = reservationRepository.findByStaff_Id(staffId);
        }

        return list.stream()
                .map(r -> modelMapper.map(r, StaffReservationDto.class))
                .collect(Collectors.toList());
    }

}
