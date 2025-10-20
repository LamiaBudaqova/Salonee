package com.backend134.salon.dtos.reservation;

import jakarta.validation.constraints.*;
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
public class ReservationCreateDto {
    @NotNull
    private Long branchId;

    @NotNull
    private Long serviceId;

    private Long staffId;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotBlank
    private String customerName;

    @NotBlank
    private String customerPhone;

    @Email
    private String customerEmail;

    private String notes;
}
