package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminStaffCreateDto;
import com.backend134.salon.admin.dtos.AdminStaffDto;
import com.backend134.salon.admin.dtos.AdminStaffUpdateDto;
import com.backend134.salon.admin.services.AdminStaffService;
import com.backend134.salon.models.Branch;
import com.backend134.salon.models.Staff;
import com.backend134.salon.repositories.BranchRepository;
import com.backend134.salon.staff.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminStaffServiceImpl implements AdminStaffService {

    private final StaffRepository staffRepository;
    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<AdminStaffDto> getAll() {
        return staffRepository.findAll().stream()
                .map(staff -> {
                    AdminStaffDto dto = modelMapper.map(staff, AdminStaffDto.class);

                    // ✅ Branch null olsa belə, səhv atmasın
                    dto.setBranchName(
                            staff.getBranch() != null ? staff.getBranch().getName() : "—"
                    );

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public AdminStaffUpdateDto getById(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usta tapılmadı"));

        AdminStaffUpdateDto dto = modelMapper.map(staff, AdminStaffUpdateDto.class);

        if (staff.getBranch() != null) {
            dto.setBranchId(staff.getBranch().getId());
        }

        return dto;
    }

    @Override
    public void create(AdminStaffCreateDto dto) {
        Staff staff = modelMapper.map(dto, Staff.class);

        // ✅ Branch varsa təyin et
        if (dto.getBranchId() != null) {
            Branch branch = branchRepository.findById(dto.getBranchId()).orElse(null);
            staff.setBranch(branch);
        }

        // ✅ Parolu encode et
        staff.setPassword(passwordEncoder.encode(dto.getPassword()));

        // ✅ Default rolu və aktivlik
        staff.setActive(dto.getActive() != null ? dto.getActive() : true);

        staffRepository.save(staff);
    }

    @Override
    public void update(AdminStaffUpdateDto dto) {
        Staff staff = staffRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usta tapılmadı"));

        // DTO-dan Staff-a map et
        modelMapper.map(dto, staff);

        // ✅ Branch varsa yenilə
        if (dto.getBranchId() != null) {
            Branch branch = branchRepository.findById(dto.getBranchId()).orElse(null);
            staff.setBranch(branch);
        }

        staffRepository.save(staff);
    }

    @Override
    public void delete(Long id) {
        staffRepository.deleteById(id);
    }
}
