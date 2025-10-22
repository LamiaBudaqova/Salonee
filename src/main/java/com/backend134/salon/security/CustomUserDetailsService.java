package com.backend134.salon.security;

import com.backend134.salon.models.User;
import com.backend134.salon.models.Staff;
import com.backend134.salon.repositories.UserRepository;
import com.backend134.salon.staff.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        String lookup = email == null ? "" : email.trim().toLowerCase();

        // 🔹 Əvvəlcə STAFF cədvəlində axtar
        Optional<Staff> staffOpt = staffRepository.findByEmail(lookup);
        if (staffOpt.isPresent()) {
            Staff staff = staffOpt.get();

            if (Boolean.FALSE.equals(staff.getActive())) {
                throw new UsernameNotFoundException("Staff is inactive");
            }

            return new org.springframework.security.core.userdetails.User(
                    staff.getEmail().trim().toLowerCase(),
                    staff.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_STAFF"))
            );
        }

        // 🔹 Sonra USER cədvəlində axtar (Admin və ya normal user)
        Optional<User> userOpt = userRepository.findByEmail(lookup);
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail().trim().toLowerCase(),
                    user.getPassword(),
                    List.of(new SimpleGrantedAuthority(user.getRole().name()))
            );
        }

        throw new UsernameNotFoundException("User or Staff not found with email: " + email);
    }

    // 🔹 Staff redirect üçün köməkçi metod
    public Optional<Staff> findStaffByUsername(String username) {
        return staffRepository.findByEmail(username);
    }
}
