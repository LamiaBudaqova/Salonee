package com.backend134.salon.dtos.team;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberDto {
    private String name;
    private String position;
    private String imageUrl;
    private String facebook;
    private String instagram;
}
