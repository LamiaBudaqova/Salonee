package com.backend134.salon.repositories;

import com.backend134.salon.models.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByBranchId(Long branchId);
}
