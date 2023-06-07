package com.saturno.bolao.repository;

import com.saturno.bolao.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    boolean existsByName(String name);

    List<Team> findByIdIn(List<Long> teamIds);
}
