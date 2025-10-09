package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminPriceCreateDto;
import com.backend134.salon.admin.dtos.AdminPriceResponseDto;
import com.backend134.salon.admin.dtos.AdminPriceUpdateDto;
import com.backend134.salon.admin.services.AdminPriceService;
import com.backend134.salon.models.Price;
import com.backend134.salon.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPriceServiceImpl implements AdminPriceService {

    private final PriceRepository priceRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AdminPriceResponseDto> getAll() {
        return priceRepository.findAll()
                .stream()
                .map(price -> modelMapper.map(price, AdminPriceResponseDto.class))
                .toList();
    }

    @Override
    public AdminPriceResponseDto getById(Long id) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qiymət tapılmadı"));
        return modelMapper.map(price, AdminPriceResponseDto.class);
    }

    @Override
    public void create(AdminPriceCreateDto dto) {
        Price price = modelMapper.map(dto, Price.class);
        priceRepository.save(price);
    }

    @Override
    public void update(Long id, AdminPriceUpdateDto dto) {
        Price price = priceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Qiymət tapılmadı"));

        price.setServiceName(dto.getServiceName());
        price.setAmount(dto.getAmount());

        priceRepository.save(price);
    }

    @Override
    public void delete(Long id) {
        priceRepository.deleteById(id);
    }
}
