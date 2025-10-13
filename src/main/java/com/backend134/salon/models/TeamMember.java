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
@Table(name = "team_members")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private String imageUrl;
    private String facebook;
    private String instagram;
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

}
