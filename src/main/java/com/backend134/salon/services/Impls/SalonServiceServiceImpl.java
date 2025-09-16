package com.backend134.salon.services.Impls;

import com.backend134.salon.models.SalonService;
import com.backend134.salon.repositories.SalonServiceRepository;
import com.backend134.salon.services.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceServiceImpl implements SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;

    @Override
    public List<SalonService> getAllServices() {
        return salonServiceRepository.findAll();
    }

    @Override
    public List<SalonService> getServicesByCategoryId(Long categoryId) {
        return salonServiceRepository.findByCategoryId(categoryId);
    }
}
