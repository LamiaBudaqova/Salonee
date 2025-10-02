package com.backend134.salon.services;

import com.backend134.salon.dtos.price.PriceResponse;

import java.util.List;

public interface PriceService {
    List<PriceResponse> getAllPrices();
}
