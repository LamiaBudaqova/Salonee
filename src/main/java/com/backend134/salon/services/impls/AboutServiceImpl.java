package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.about.AboutHomeDto;
import com.backend134.salon.repositories.AboutRepository;
import com.backend134.salon.services.AboutService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AboutServiceImpl implements AboutService {

    private final AboutRepository aboutRepository;
    private final ModelMapper modelMapper;

    @Override
    public AboutHomeDto getAboutForHome() {
        return aboutRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .map(about -> {
                    AboutHomeDto dto = modelMapper.map(about, AboutHomeDto.class);
                    if (dto.getDescription() != null && dto.getDescription().length() > 120) {
                        String shortDesc = dto.getDescription().substring(0, 120) + "...";
                        dto.setDescription(shortDesc);
                    }
                    return dto;
                })
                .orElse(null);

    }

    @Override
    public AboutHomeDto getAboutForPage() {
        return aboutRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .map(about -> modelMapper.map(about, AboutHomeDto.class))
                .orElse(null);

    }
}