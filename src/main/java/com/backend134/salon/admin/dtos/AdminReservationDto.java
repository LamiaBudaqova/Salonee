package com.backend134.salon.admin.dtos;

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
public class AdminReservationDto {
    private Long id;

    private String serviceName;
    private String staffName;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private String customerName;
    private String customerPhone;
    private ReservationStatus status;
    private String notes;
}
