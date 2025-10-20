package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.salonservice.SalonServiceDto;
import com.backend134.salon.models.SalonService;
import com.backend134.salon.repositories.SalonServiceRepository;
import com.backend134.salon.repositories.StaffRepository;
import com.backend134.salon.services.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalonServiceServiceImpl implements SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;
    private final StaffRepository staffRepository;

    // 🔹 Entity → DTO çevirmə metodu
    private SalonServiceDto mapToDto(SalonService service) {
        SalonServiceDto dto = new SalonServiceDto();
        dto.setId(service.getId());
        dto.setName(service.getName());
        dto.setDescription(service.getDescription());
        dto.setCategoryName(service.getCategory() != null ? service.getCategory().getName() : null);
        dto.setExtraInfo(service.getExtraInfo());
        return dto;
    }

    @Override
    public List<SalonServiceDto> getServicesByCategoryId(Long categoryId) {
        return salonServiceRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SalonServiceDto getServiceById(Long id) {
        return salonServiceRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    @Override
    public List<SalonService> getAll() {
        return salonServiceRepository.findAll();
    }

    // 🔹 Filial üzrə xidmətlər
    @Override
    public List<SalonServiceDto> getByBranch(Long branchId) {
        List<SalonService> services = staffRepository.findServicesByBranchId(branchId);
        if (services == null || services.isEmpty()) {
            return List.of(); // boş qaytar, səhv yox
        }

        return services.stream()
                .distinct()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 🔹 Usta üzrə xidmətlər
    @Override
    public List<SalonServiceDto> getByStaff(Long staffId) {
        List<SalonService> services = staffRepository.findServicesByStaffId(staffId);
        if (services == null || services.isEmpty()) {
            return List.of(); // boş qaytar
        }

        return services.stream()
                .distinct()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
