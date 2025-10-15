package com.backend134.salon.dtos.staff;

import com.backend134.salon.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffReservationDto {
    private Long id;                // rezervin ID-si
    private String serviceName;
    private LocalDate date;
    private LocalTime startTime;    // başlama vaxtı
    private String customerName;
    private String customerPhone;
    private ReservationStatus status; // rezervasiyanın veziyyeti (PENDING, APPROVED, DONE)
}
