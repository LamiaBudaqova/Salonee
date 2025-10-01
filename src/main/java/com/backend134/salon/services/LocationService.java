package com.backend134.salon.services;

import com.backend134.salon.dtos.location.LocationCreateDto;
import com.backend134.salon.dtos.location.LocationDashboardDto;
import com.backend134.salon.dtos.location.LocationDto;
import com.backend134.salon.dtos.location.LocationUpdateDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocations();
    LocationDto getLocationById(Long id);

    void createLocation(LocationCreateDto dto);
    void updateLocation(LocationUpdateDto dto);
    void deleteLocation(Long id);

    List<LocationDashboardDto> getDashboardLocations();
}
