package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.contact.ContactDto;
import com.backend134.salon.models.Contact;
import com.backend134.salon.repositories.ContactRepository;
import com.backend134.salon.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .filter(Contact::isActive)
                .map(contact -> modelMapper.map(contact, ContactDto.class))
                .toList();
    }
}