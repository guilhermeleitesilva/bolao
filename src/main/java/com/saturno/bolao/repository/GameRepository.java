package com.saturno.bolao.repository;

import com.saturno.bolao.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
