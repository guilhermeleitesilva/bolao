package com.saturno.bolao.dto;

import com.saturno.bolao.entity.Championship;
import com.saturno.bolao.entity.Game;
import com.saturno.bolao.entity.Team;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

public record GameRecordDto(@NotBlank(message = "Stadium is required")
                            String stadium,
                            @NotNull(message = "Date is required")
                            Instant date,
                            Integer homeScore,
                            Integer visitorScore,
                            @NotNull(message = "Home team id is required")
                            Long homeTeam,
                            @NotNull(message = "Visitor team id is required")
                            Long visitorTeam,
                            @NotNull(message = "Championship id is required")
                            Long championshipId) {
    public Game convertToGame(){
        Game game = new Game();
        BeanUtils.copyProperties(this, game);
        game.setCreateDate(Instant.now());
        Championship championship = new Championship();
        championship.setId(this.championshipId);

        Team homeTeam = new Team();
        homeTeam.setId(this.homeTeam);

        Team visitorTeam = new Team();
        visitorTeam.setId(this.visitorTeam);

        game.setChampionship(championship);
        game.setHomeTeam(homeTeam);
        game.setVisitorTeam(visitorTeam);

        return game;
    }
}
