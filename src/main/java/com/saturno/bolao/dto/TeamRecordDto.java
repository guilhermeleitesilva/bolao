package com.saturno.bolao.dto;

import com.saturno.bolao.entity.Team;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

public record TeamRecordDto (@NotBlank(message = "Name is required")
                             String name,
                             String nickname,
                             String image) {
    public Team convertToTeam() {
        Team team = new Team();
        BeanUtils.copyProperties(this, team);
        team.setCreateDate(Instant.now());
        return team;
    }
}
