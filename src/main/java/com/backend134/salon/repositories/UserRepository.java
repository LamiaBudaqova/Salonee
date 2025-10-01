package com.backend134.salon.repositories;

import com.backend134.salon.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // login ucun lazım olacaq
}
