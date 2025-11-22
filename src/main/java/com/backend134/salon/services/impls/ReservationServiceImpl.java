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

        // xidmet tapılır
        SalonService service = salonServiceRepository.findById(dto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Xidmət tapılmadı"));

        // usta secilibse tapılır
        Staff staff = null;
        if (dto.getStaffId() != null) {
            staff = staffRepository.findById(dto.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Usta tapılmadı"));
        }

        //  3. 30 dqlik xidmet bitiş vaxtı
        LocalTime end = dto.getStartTime().plusMinutes(30);

        // vaxt toqquşması yoxlanır
        var conflicts = reservationRepository.findConflicts(
                dto.getDate(), dto.getStartTime(), end,
                staff != null ? staff.getId() : null
        );
        if (!conflicts.isEmpty()) {
            throw new IllegalStateException("Bu vaxt artıq doludur!");
        }

        // login olmuş istifadecini tapırıq
        String email = SecurityUtil.getLoggedUserEmail();
        User user = null;
        if (email != null) {
            user = userRepository.findByEmail(email).orElse(null);
        }

        // xidmetin tam qiymeti
        double price = service.getPrice();
        double usedCashback = 0.0;

        // eger user varsa ve formda "cashback istifadə et" secilibse
        if (user != null && dto.isUseCashback()) {

            double balance = user.getCashbackBalance();

            if (balance > 0) {
                if (balance >= price) {
                    // balans xidmetin qiymetinden çoxdur - xidmet pulsuz
                    usedCashback = price;
                    user.setCashbackBalance(balance - price);
                } else {
                    // balans qiymetden azdır - balans qeder endirim
                    usedCashback = balance;
                    user.setCashbackBalance(0.0);
                }
                userRepository.save(user);
            }
        }

        // rezervasiya obyektinin yaradılması
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
        r.setUsedCashback(usedCashback);

        if (user != null) {
            r.setUser(user);
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
        double usedCashback = reservation.getUsedCashback() != null ? reservation.getUsedCashback() : 0.0;

        // musterinin real odediyi mebleg
        double paidAmount = price - usedCashback;
        if (paidAmount < 0) paidAmount = 0;

        double cashback = paidAmount * 0.02;

        // user varsa cashback elave edilir
        if (reservation.getUser() != null && cashback > 0) {
            User user = reservation.getUser();
            user.setCashbackBalance(user.getCashbackBalance() + cashback);
            userRepository.save(user);
        }

        // TELEGRAM MESAJI
        String message = String.format(
                "Salam %s! 🌸\n" +
                        "Sizin '%s' xidmətinə rezervasiyanız qəbul olundu ✅\n\n" +
                        "📅 Tarix: %s\n" +
                        "⏰ Saat: %s\n" +
                        "💵 Qiymət: %.2f₼\n" +
                        "💳 İstifadə olunan cashback: %.2f₼\n" +
                        "💰 Ödəniləcək məbləğ: %.2f₼\n" +
                        "🎁 Yeni keşbek: %.2f₼ balansınıza əlavə olundu! 💖",
                reservation.getCustomerName(),
                reservation.getService().getName(),
                reservation.getDate(),
                reservation.getStartTime(),
                price,
                usedCashback,
                paidAmount,
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
