package com.backend134.salon.dtos.reservation;

import com.backend134.salon.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUpdateDto {
    private Long id;
    private ReservationStatus status;
    private String notes;
}

