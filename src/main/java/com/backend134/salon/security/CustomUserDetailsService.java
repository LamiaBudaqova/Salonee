package com.backend134.salon.security;

import com.backend134.salon.models.User;
import com.backend134.salon.models.Staff;
import com.backend134.salon.repositories.UserRepository;
import com.backend134.salon.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 🔹 Əvvəlcə adi istifadəçilərdə (admin və ya user) axtar
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole().name()))
            );
        }

        // 🔹 Tapılmadısa, ustalarda (staff) axtar — EMAIL ilə
        Optional<Staff> staffOpt = staffRepository.findByEmail(email);
        if (staffOpt.isPresent()) {
            Staff staff = staffOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    staff.getEmail(), // login üçün email istifadə olunur
                    staff.getPassword(),
                    List.of(new SimpleGrantedAuthority(staff.getRole().name()))
            );
        }

        // 🔹 Heç kim tapılmadısa:
        throw new UsernameNotFoundException("User or Staff not found with email: " + email);
    }

    // 🔹 Bu metodu SecurityConfig-də redirect üçün istifadə edəcəyik
    public Optional<Staff> findStaffByUsername(String username) {
        return staffRepository.findByEmail(username);
    }
}
