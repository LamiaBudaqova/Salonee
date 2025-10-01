package com.backend134.salon.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String confirmPassword; // şifre testiqi ucun

}
