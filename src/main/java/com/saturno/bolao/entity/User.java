package com.saturno.bolao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="user")
public class User extends RepresentationModel<User> implements Serializable {

    @Serial
    private static final long serialVersionUID = -2751982117362493029L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, unique = true, nullable = false)
    private String email;

    @Column(length = 20, unique = true, nullable = false)
    private String username;

}
