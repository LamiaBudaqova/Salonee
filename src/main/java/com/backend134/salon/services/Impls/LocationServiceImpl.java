package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.location.LocationCreateDto;
import com.backend134.salon.dtos.location.LocationDashboardDto;
import com.backend134.salon.dtos.location.LocationDto;
import com.backend134.salon.dtos.location.LocationUpdateDto;
import com.backend134.salon.models.Location;
import com.backend134.salon.repositories.LocationRepository;
import com.backend134.salon.services.LocationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(loc -> modelMapper.map(loc, LocationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LocationDto getLocationById(Long id) {
        Location location = locationRepository.findById(id).orElseThrow();
        return modelMapper.map(location, LocationDto.class);
    }

    @Override
    public void createLocation(LocationCreateDto dto) {
        Location location = modelMapper.map(dto, Location.class);
        locationRepository.save(location);
    }

    @Override
    public void updateLocation(LocationUpdateDto dto) {
        Location location = locationRepository.findById(dto.getId()).orElseThrow();
        location.setAddress(dto.getAddress());
        location.setPhone(dto.getPhone());
        location.setEmail(dto.getEmail());
        location.setMapLink(dto.getMapLink());
        location.setActive(dto.isActive());
        locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public List<LocationDashboardDto> getDashboardLocations() {
        return locationRepository.findAll()
                .stream()
                .map(loc -> modelMapper.map(loc, LocationDashboardDto.class))
                .collect(Collectors.toList());
    }
}