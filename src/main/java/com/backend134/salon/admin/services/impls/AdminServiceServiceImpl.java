package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminServiceCreateDto;
import com.backend134.salon.admin.dtos.AdminServiceResponseDto;
import com.backend134.salon.admin.dtos.AdminServiceUpdateDto;
import com.backend134.salon.admin.services.AdminServiceService;
import com.backend134.salon.models.Category;
import com.backend134.salon.models.SalonService;
import com.backend134.salon.repositories.CategoryRepository;
import com.backend134.salon.repositories.SalonServiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceServiceImpl implements AdminServiceService {

    private final SalonServiceRepository salonServiceRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminServiceResponseDto> getAll() {
        return salonServiceRepository.findAll().stream()
                .map(service -> {
                    AdminServiceResponseDto dto = modelMapper.map(service, AdminServiceResponseDto.class);
                    dto.setCategoryName(service.getCategory() != null ? service.getCategory().getName() : "Yoxdur");
                    dto.setCategoryId(service.getCategory() != null ? service.getCategory().getId() : null);
                    return dto;
                })
                .toList();
    }

    @Override
    public AdminServiceResponseDto getById(Long id) {
        SalonService service = salonServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Xidmət tapılmadı"));
        AdminServiceResponseDto dto = modelMapper.map(service, AdminServiceResponseDto.class);
        dto.setCategoryId(service.getCategory() != null ? service.getCategory().getId() : null);
        dto.setCategoryName(service.getCategory() != null ? service.getCategory().getName() : "Yoxdur");
        return dto;
    }

    @Override
    public void create(AdminServiceCreateDto dto) {
        SalonService service = modelMapper.map(dto, SalonService.class);

        service.setPrice(dto.getPrice());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
        service.setCategory(category);

        salonServiceRepository.save(service);
    }

    @Override
    public void update(AdminServiceUpdateDto dto) {
        SalonService service = salonServiceRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Xidmət tapılmadı"));

        service.setName(dto.getName());
        service.setDescription(dto.getDescription());
        service.setImage(dto.getImage());
        service.setExtraInfo(dto.getExtraInfo());

        service.setPrice(dto.getPrice());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kateqoriya tapılmadı"));
        service.setCategory(category);

        salonServiceRepository.save(service);
    }


    @Override
    public void delete(Long id) {
        salonServiceRepository.deleteById(id);
    }
}

