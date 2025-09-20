package com.backend134.salon.services.Impls;

import com.backend134.salon.dtos.hero.HeroDto;
import com.backend134.salon.models.Hero;
import com.backend134.salon.repositories.HeroRepository;
import com.backend134.salon.services.HeroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;

    @Override
    public HeroDto getHero() {
        return heroRepository.findAll()
                .stream()
                .findFirst()
                .map(hero -> modelMapper.map(hero, HeroDto.class))
                .orElse(null);
    }
}
