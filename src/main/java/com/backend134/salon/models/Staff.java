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
    private String password;

    private String fullName;
    private String phone;
    @Column(unique = true, nullable = false, length = 255)
    private String email;
    private String position; // 🔹 əlavə etdik — profil üçün görünəcək
    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_STAFF;

    private Boolean active = true;

    // 🔹 Hər usta bir filialda işləyir
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    // 🔹 Hər ustanın bir neçə xidməti ola bilər
    @ManyToMany
    @JoinTable(
            name = "staff_services",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<SalonService> services = new HashSet<>();
}
