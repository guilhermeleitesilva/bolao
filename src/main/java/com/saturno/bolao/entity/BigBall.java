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
@Table(name="big_ball")
public class BigBall extends RepresentationModel<BigBall> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5158210939282253231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private Instant createDate;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

}
