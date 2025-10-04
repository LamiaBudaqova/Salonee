package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.models.Role;
import com.backend134.salon.models.User;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return false;
        }

        if (userRepository.findByEmail(userRegisterDto.getEmail()).isPresent()) {
            return false;
        }

        User user = new User();
        user.setName(userRegisterDto.getName());
        user.setSurname(userRegisterDto.getSurname());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        // default role
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("İstifadəçi tapılmadı: " + email));
    }
}