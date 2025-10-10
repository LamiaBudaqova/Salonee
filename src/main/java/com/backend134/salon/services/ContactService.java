package com.backend134.salon.services;

import com.backend134.salon.dtos.contact.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> getAllContacts();
}