package com.saturno.bolao.dto;

import com.saturno.bolao.entity.BigBall;
import com.saturno.bolao.entity.Championship;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

public record BigBallRecordDto(@NotBlank(message = "Name is required")
                               String name,
                               @NotNull(message = "Championship is required")
                               Long championshipId) {

    public BigBall convertToBigBall(){
        BigBall bigBall = new BigBall();
        BeanUtils.copyProperties(this, bigBall);
        bigBall.setCreateDate(Instant.now());
        Championship championship = new Championship();
        championship.setId(this.championshipId);
        bigBall.setChampionship(championship);
        return bigBall;
    }
}
