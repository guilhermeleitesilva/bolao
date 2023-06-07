package com.saturno.bolao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Setter
@Getter
@Table(name="game")
public class Game extends RepresentationModel<Game> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1703190040373330467L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Instant date;

    private String stadium;

    private Integer homeScore;

    private Integer visitorScore;

    private Instant createDate;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    private Team visitorTeam;

}
