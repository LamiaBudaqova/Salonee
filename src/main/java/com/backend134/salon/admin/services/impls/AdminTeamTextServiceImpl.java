package com.backend134.salon.admin.services.impls;

import com.backend134.salon.admin.dtos.AdminTeamTextResponseDto;
import com.backend134.salon.admin.dtos.AdminTeamTextUpdateDto;
import com.backend134.salon.admin.services.AdminTeamTextService;
import com.backend134.salon.models.TeamText;
import com.backend134.salon.repositories.TeamTextRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminTeamTextServiceImpl implements AdminTeamTextService {
    private final TeamTextRepository teamTextRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminTeamTextResponseDto getTeamText() {
        TeamText text = teamTextRepository.findAll().stream().findFirst().orElse(null);
        return text != null ? modelMapper.map(text, AdminTeamTextResponseDto.class) : null;
    }

    @Override
    public void updateTeamText(Long id, AdminTeamTextUpdateDto dto) {
        TeamText text = teamTextRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team text tapılmadı"));
        text.setTitle(dto.getTitle());
        text.setSubtitle(dto.getSubtitle());
        teamTextRepository.save(text);
    }
}

