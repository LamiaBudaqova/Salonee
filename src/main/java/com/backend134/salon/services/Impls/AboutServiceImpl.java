package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.about.AboutHomeDto;
import com.backend134.salon.models.About;
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
    public AboutHomeDto getAboutContent() {
        return aboutRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .map(about -> modelMapper.map(about, AboutHomeDto.class))
                .orElse(null);
    }
}
