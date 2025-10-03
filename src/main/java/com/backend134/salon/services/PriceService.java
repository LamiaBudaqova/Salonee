package com.backend134.salon.services;

import com.backend134.salon.dtos.price.PriceResponse;
import com.backend134.salon.models.Price;

import java.util.List;

public interface PriceService {
    List<Price> getHomePrices();   // yalnız ilk 5 qiymət
    List<PriceResponse> getAllPrices();
}
