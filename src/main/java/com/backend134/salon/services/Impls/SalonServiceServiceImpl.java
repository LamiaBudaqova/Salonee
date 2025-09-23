package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.salonservice.SalonServiceDto;
import com.backend134.salon.models.SalonService;
import com.backend134.salon.repositories.SalonServiceRepository;
import com.backend134.salon.services.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceServiceImpl implements SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;
    private SalonServiceDto mapToDto(SalonService service) {
        SalonServiceDto dto = new SalonServiceDto();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setPrice(service.getPrice());
        dto.setImage(service.getImage());
        dto.setCategoryName(service.getCategory() != null ? service.getCategory().getName() : null);
        return dto;
    }


    @Override
    public List<SalonServiceDto> getAllServices() {
        List<SalonService> services = salonServiceRepository.findAll();
        List<SalonServiceDto> dtos = new ArrayList<>();
        for (SalonService service : services) {
            dtos.add(mapToDto(service));
        }
        return dtos;
    }

    @Override
    public List<SalonServiceDto> getServicesByCategoryId(Long categoryId) {
        List<SalonService> services = salonServiceRepository.findByCategoryId(categoryId);
        List<SalonServiceDto> dtos = new ArrayList<>();
        for (SalonService service : services) {
            dtos.add(mapToDto(service));
        }
        return dtos;
    }

    @Override
    public SalonServiceDto getServiceById(Long id) {
        SalonService service = salonServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return mapToDto(service);
    }
}