package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.price.PriceResponse;
import com.backend134.salon.models.Price;
import com.backend134.salon.repositories.PriceRepository;
import com.backend134.salon.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public List<PriceResponse> getAllPrices() {
        return priceRepository.findAll().stream()
                .map(p -> new PriceResponse(p.getId(), p.getServiceName(), p.getAmount()))
                .collect(Collectors.toList());
    }
}