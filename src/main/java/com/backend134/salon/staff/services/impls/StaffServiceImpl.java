package com.backend134.salon.staff.services.impls;

import com.backend134.salon.staff.dtos.StaffSimpleDto;
import com.backend134.salon.models.Staff;
import com.backend134.salon.staff.repositories.StaffRepository;
import com.backend134.salon.staff.services.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StaffSimpleDto> getAll() {
        return staffRepository.findAll()
                .stream()
                .filter(Staff::getActive)
                .map(staff -> modelMapper.map(staff, StaffSimpleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffSimpleDto> getByServiceId(Long serviceId) {
        return staffRepository.findByServices_Id(serviceId)
                .stream()
                .filter(Staff::getActive)
                .map(staff -> modelMapper.map(staff, StaffSimpleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StaffSimpleDto> getByBranch(Long branchId) {
        return staffRepository.findByBranch_Id(branchId)
                .stream()
                .filter(Staff::getActive)
                .map(staff -> modelMapper.map(staff, StaffSimpleDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<StaffSimpleDto> getByBranchAndService(Long branchId, Long serviceId) {
        return staffRepository.findByBranch_IdAndServices_Id(branchId, serviceId)
                .stream()
                .filter(Staff::getActive)
                .map(staff -> modelMapper.map(staff, StaffSimpleDto.class))
                .collect(Collectors.toList());
    }
}