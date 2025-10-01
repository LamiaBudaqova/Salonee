package com.backend134.salon.dtos.contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactMessageDto {
    private String name;
    private String email;
    private String subject; //movzu
    private String message;
}
