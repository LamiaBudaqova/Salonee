package com.backend134.salon.models;

import com.backend134.salon.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 60)
    private String username;

    @Column(nullable = false)
    private String password; // login üçün

    private String fullName;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_STAFF;

    private Boolean active = true;

    // her ustanın bir nece xidmeti ola biler
    @ManyToMany
    @JoinTable(
            name = "staff_services",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<SalonService> services = new HashSet<>();
}