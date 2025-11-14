package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.reservation.ReservationCreateDto;
import com.backend134.salon.enums.ReservationStatus;
import com.backend134.salon.models.*;
import com.backend134.salon.repositories.*;
import com.backend134.salon.security.SecurityUtil;
import com.backend134.salon.services.ReservationService;
import com.backend134.salon.services.TelegramNotificationService;
import com.backend134.salon.staff.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final TelegramNotificationService telegramNotificationService;

    /**
     * REZERVASİYA YARATMA
     */
    @Override
    public Long create(ReservationCreateDto dto) {

        // ⭐ Xidmət tapılır
        SalonService service = salonServiceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Xidmət tapılmadı"));

        // ⭐ Usta seçilibsə tapılır
        Staff staff = null;
        if (dto.getStaffId() != null) {
            staff = staffRepository.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Usta tapılmadı"));
        }

        // ⭐ 30 dəqiqəlik xidmət bitiş vaxtı
        LocalTime end = dto.getStartTime().plusMinutes(30);

        // ⭐ Vaxt toqquşması yoxlanır
        var conflicts = reservationRepository.findConflicts(
                dto.getDate(), dto.getStartTime(), end,
                staff != null ? staff.getId() : null
        );
        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("Bu vaxt artıq doludur!");
        }

        // ⭐ Rezervasiya obyektinin yaradılması
        Reservation r = new Reservation();
        r.setService(service);
        r.setStaff(staff);
        r.setDate(dto.getDate());
        r.setStartTime(dto.getStartTime());
        r.setEndTime(end);
        r.setStatus(ReservationStatus.PENDING);
        r.setCustomerName(dto.getCustomerName());
        r.setCustomerPhone(dto.getCustomerPhone());
        r.setNotes(dto.getNotes());

        // ⭐ Login olmuş istifadəçini rezervə bağlama
        String email = SecurityUtil.getLoggedUserEmail();
        if (email != null) {
            userRepository.findByEmail(email).ifPresent(r::setUser);
        }

        reservationRepository.save(r);
        return r.getId();
    }

    /**
     * REZERVASİYA TƏSDİQLƏNMƏSİ (APPROVE)
     */
    @Override
    @Transactional
    public void approveReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezerv tapılmadı"));

        reservation.setStatus(ReservationStatus.APPROVED);
        reservationRepository.save(reservation);

        double price = reservation.getService().getPrice();
        double cashback = price * 0.02; // 2% keşbek

        // ⭐ USER VARSA – CASHBACK ƏLAVƏ EDİLİR
        if (reservation.getUser() != null) {
            User user = reservation.getUser();
            user.setCashbackBalance(user.getCashbackBalance() + cashback);
            userRepository.save(user);
        }

        // ⭐ TELEGRAM MESAJI
        String message = String.format(
                "Salam %s! 🌸\n" +
                        "Sizin '%s' xidmətinə rezervasiyanız qəbul olundu ✅\n\n" +
                        "📅 Tarix: %s\n" +
                        "⏰ Saat: %s\n" +
                        "💵 Qiymət: %.2f₼\n" +
                        "🎁 Keşbek: %.2f₼ balansınıza əlavə olundu! 💖",
                reservation.getCustomerName(),
                reservation.getService().getName(),
                reservation.getDate(),
                reservation.getStartTime(),
                price,
                cashback
        );

        telegramNotificationService.sendTelegramMessage(
                reservation.getCustomerPhone(), message
        );
    }

    /**
     * REZERVASİYA RƏDD EDİLMƏSİ
     */
    @Override
    @Transactional
    public void rejectReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezerv tapılmadı"));

        reservation.setStatus(ReservationStatus.REJECTED);
        reservationRepository.save(reservation);

        String message = String.format(
                "Salam %s! 😔\n" +
                        "'%s' xidmətinə rezervasiyanız təəssüf ki qəbul edilmədi.\n" +
                        "Xahiş edirik başqa tarix seçəsiniz 💅",
                reservation.getCustomerName(),
                reservation.getService().getName()
        );

        telegramNotificationService.sendTelegramMessage(
                reservation.getCustomerPhone(), message
        );
    }
}
