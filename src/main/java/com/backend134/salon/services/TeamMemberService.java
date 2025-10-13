package com.backend134.salon.services;

import com.backend134.salon.dtos.team.TeamMemberDto;
import com.backend134.salon.models.TeamMember;

import java.util.List;

public interface TeamMemberService {
    List<TeamMemberDto> getAllTeamMembers();
    List<TeamMemberDto> getFirstFourMembers(); // yalnız ilk 4 nəfər
    List<TeamMemberDto> getByBranch(Long branchId);
}
