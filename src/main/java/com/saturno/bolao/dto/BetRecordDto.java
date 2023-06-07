package com.saturno.bolao.dto;

import com.saturno.bolao.entity.Bet;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

public record BetRecordDto(@NotBlank(message = "Game id is required")
                           Long gameId,
                           @NotBlank(message = "User id is required")
                           Long userId) {

    public Bet convertToBet(){
        Bet bet = new Bet();
        BeanUtils.copyProperties(this, bet);
        return bet;
    }
}
