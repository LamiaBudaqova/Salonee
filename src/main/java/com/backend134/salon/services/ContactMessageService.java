package com.backend134.salon.services;

import com.backend134.salon.dtos.contact.ContactMessageDto;

public interface ContactMessageService {
    void saveMessage(ContactMessageDto contactMessageDto);

}
