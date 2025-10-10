package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminContactCreateDto;
import com.backend134.salon.admin.dtos.AdminContactResponseDto;
import com.backend134.salon.admin.dtos.AdminContactUpdateDto;
import com.backend134.salon.admin.services.AdminContactService;
import com.backend134.salon.models.Contact;
import com.backend134.salon.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminContactServiceImpl implements AdminContactService {

    private final ContactRepository contactRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminContactResponseDto> getAll() {
        return contactRepository.findAll()
                .stream()
                .map(contact -> modelMapper.map(contact, AdminContactResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminContactResponseDto getById(Long id) {
        // ⚠️ Əgər tapılmasa, null qaytarır (500 error atmasın deyə)
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact == null) {
            return null;
        }
        return modelMapper.map(contact, AdminContactResponseDto.class);
    }

    @Override
    public void create(AdminContactCreateDto dto) {
        Contact contact = modelMapper.map(dto, Contact.class);
        contactRepository.save(contact);
    }

    @Override
    public void update(AdminContactUpdateDto dto) {
        Contact contact = contactRepository.findById(dto.getId()).orElse(null);
        if (contact == null) return;

        contact.setAddress(dto.getAddress());
        contact.setPhone(dto.getPhone());
        contact.setEmail(dto.getEmail());
        contact.setMapLink(dto.getMapLink());
        contact.setActive(dto.isActive());

        contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
