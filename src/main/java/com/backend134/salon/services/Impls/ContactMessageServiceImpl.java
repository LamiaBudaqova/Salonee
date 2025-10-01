package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.contact.ContactMessageDto;
import com.backend134.salon.models.ContactMessage;
import com.backend134.salon.repositories.ContactMessageRepository;
import com.backend134.salon.services.ContactMessageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageServiceImpl implements ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveMessage(ContactMessageDto contactMessageDto) {
        ContactMessage message = modelMapper.map(contactMessageDto, ContactMessage.class);

        contactMessageRepository.save(message);
    }
}
