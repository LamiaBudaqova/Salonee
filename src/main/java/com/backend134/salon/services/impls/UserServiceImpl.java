package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.user.UserRegisterDto;
import com.backend134.salon.enums.Role;
import com.backend134.salon.models.User;
import com.backend134.salon.repositories.UserRepository;
import com.backend134.salon.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public User updateProfile(Long id, String name, String surname, String email) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tapılmadı"));

        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOptionalByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}