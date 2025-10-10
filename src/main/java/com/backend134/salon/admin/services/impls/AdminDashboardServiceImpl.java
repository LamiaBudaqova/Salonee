package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.services.AdminDashboardService;
import com.backend134.salon.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final BlogRepository blogRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final ContactRepository contactRepository;
    private final CategoryRepository categoryRepository;
    private final GalleryImageRepository galleryImageRepository;

    @Override
    public Map<String, Long> getStatistics() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("blogs", blogRepository.count());
        stats.put("services", salonServiceRepository.count());
        stats.put("messages", contactRepository.count());
        stats.put("categories", categoryRepository.count());
        stats.put("gallery", galleryImageRepository.count());

        return stats;
    }
}

