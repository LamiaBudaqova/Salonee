package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.reservation.ReservationDto;
import com.backend134.salon.models.User;
import com.backend134.salon.repositories.ReservationRepository;
import com.backend134.salon.repositories.UserRepository;
import com.backend134.salon.services.UserReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserReservationServiceImpl implements UserReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Override
    public List<ReservationDto> getUserReservations(Long userId) {

        User user = userRepository.findById(userId) // user email tapılır
                .orElseThrow(() -> new RuntimeException("User tapılmadı"));

        String email = user.getEmail();

        return reservationRepository  // usere aid butun rezervler cekilir tarix sırasına gore
                .findByUser_EmailOrderByDateDescStartTimeAsc(email)
                .stream()
                .map(r -> new ReservationDto(
                        r.getId(),
                        r.getService().getName(),
                        r.getStaff() != null ? r.getStaff().getFullName() : "-",
                        r.getDate(),
                        r.getStartTime(),
                        r.getEndTime(),
                        r.getStatus(),
                        r.getCustomerName(),
                        r.getCustomerPhone()
                ))
                .toList();
    }
}