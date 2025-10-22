package com.backend134.salon.staff.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaffDashboardStatsDto {
    private long pendingCount;   // gozlemede olan rezervlerin sayı
    private long approvedCount;  // testiqlenmiş rezervlerin sayı
    private long doneCount;      // Bitmiş rezervlerin sayı
}
