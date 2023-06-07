package com.saturno.bolao.dto;

import com.saturno.bolao.entity.Championship;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

public record ChampionshipRecordDto (@NotBlank(message = "Name is required")
                                     String name,
                                     String nickname) {

    public Championship convertToChampionship(){
        Championship championship = new Championship();
        championship.setCreateDate(Instant.now());
        BeanUtils.copyProperties(this, championship);
        return championship;
    }
}
