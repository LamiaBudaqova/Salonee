package com.backend134.salon.services.impls;

import com.backend134.salon.dtos.team.TeamTextDto;
import com.backend134.salon.models.TeamText;
import com.backend134.salon.repositories.TeamTextRepository;
import com.backend134.salon.services.TeamTextService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamTextServiceImpl implements TeamTextService {

    private final TeamTextRepository teamTextRepository;
    private final ModelMapper modelMapper;

    @Override
    public TeamTextDto getText() {
        TeamText teamText = teamTextRepository.findById(1L).orElse(null);
        return modelMapper.map(teamText, TeamTextDto.class);
    }
}
