package com.saturno.bolao.repository;

import com.saturno.bolao.entity.BigBall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigBallRepository extends JpaRepository<BigBall, Long> {

    boolean existsByName(String name);
}
