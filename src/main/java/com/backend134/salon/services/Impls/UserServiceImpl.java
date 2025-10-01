package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.models.Role;
import com.backend134.salon.models.User;
import com.backend134.salon.repositories.RoleRepository;
import com.backend134.salon.repositories.UserRepository;
import com.backend134.salon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        // 1. Şifrələrin uyğunluğunu yoxlayırıq
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }

        // 2. İstifadəçi artıq mövcuddursa, qeydiyyata icazə vermirik
        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            return false;
        }

        // 3. Yeni istifadəçi obyekti yaradılır
        User user = new User();
        user.setName(userRegisterDto.getName());
        user.setSurname(userRegisterDto.getSurname());
        user.setEmail(userRegisterDto.getEmail());

        // Şifrəni encode edirik (plain text saxlamırıq!)
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        // 4. İstifadəçiyə default olaraq ROLE_USER veririk
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> {
                    // Əgər ROLE_USER tapılmazsa DB-də, avtomatik yaradılır
                    Role r = new Role();
                    r.setName("ROLE_USER");
                    return roleRepository.save(r);
                });

        user.setRoles(Collections.singletonList(roleUser));

        // 5. DB-yə save edirik
        userRepository.save(user);

        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı: " + email));
    }
}
