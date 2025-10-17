package com.backend134.salon.services.impls;

import com.backend134.salon.admin.dtos.AdminBranchCreateDto;
import com.backend134.salon.admin.dtos.AdminBranchUpdateDto;
import com.backend134.salon.dtos.branch.BranchDto;
import com.backend134.salon.models.Branch;
import com.backend134.salon.repositories.BranchRepository;
import com.backend134.salon.services.BranchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BranchDto> getAllBranches() {
        return branchRepository.findAll()
                .stream()
                .map(branch -> modelMapper.map(branch, BranchDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BranchDto> getAll() {
        return branchRepository.findAll()
                .stream()
                .map(branch -> modelMapper.map(branch, BranchDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(AdminBranchCreateDto dto) {
        Branch branch = modelMapper.map(dto, Branch.class);
        branchRepository.save(branch);
    }

    @Override
    public void update(Long id, AdminBranchUpdateDto dto) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filial tapılmadı"));
        branch.setName(dto.getName());
        branch.setAddress(dto.getAddress());
        branch.setPhone(dto.getPhone());
        branchRepository.save(branch);
    }

    @Override
    public void delete(Long id) {
        branchRepository.deleteById(id);
    }
}
