package com.saturno.bolao.repository;

import com.saturno.bolao.entity.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {

    boolean existsByName(String name);
}
