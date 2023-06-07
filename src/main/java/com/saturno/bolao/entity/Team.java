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
@Table(name="team")
public class Team extends RepresentationModel<Team> implements Serializable {

    @Serial
    private static final long serialVersionUID = -921306399466046969L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    private String image;

    @Column(length = 20, nullable = false)
    private String nickname;

    private Instant createDate;
}
