package com.backend134.salon.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "navbar_items")
public class NavbarItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;      // Linkin adı (Ana sehife, haqqımızda...)
    private String url;        // /, /about, /pricing
    private boolean active;    // gosterilsin mi ya yox
    private int orderIndex;     // Sıra (1,2,3,4)
}
