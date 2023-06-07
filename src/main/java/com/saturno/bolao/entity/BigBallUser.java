package com.saturno.bolao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name="big_ball_user")
public class BigBallUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -8383726100100341601L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "big_ball_id")
    private BigBall bigBall;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
