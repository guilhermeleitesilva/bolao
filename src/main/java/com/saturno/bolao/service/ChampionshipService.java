package com.saturno.bolao.service;

import com.saturno.bolao.entity.Championship;
import com.saturno.bolao.entity.Team;
import com.saturno.bolao.repository.ChampionshipRepository;
import com.saturno.bolao.repository.TeamRepository;
import com.saturno.bolao.service.exceptions.BadRequestException;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipService {

    private final ChampionshipRepository championshipRepository;

    private final TeamRepository teamRepository;

    public ChampionshipService(ChampionshipRepository championshipRepository, TeamRepository teamRepository) {
        this.championshipRepository = championshipRepository;
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Championship save(Championship championship) {

        if (championshipRepository.existsByName(championship.getName()))
            throw new BadRequestException("Conflict: Name is already in use!");

        return championshipRepository.save(championship);
    }

    public Page<Championship> findAll(Pageable pageable) {
        return championshipRepository.findAll(pageable);
    }

    public Championship findById(Long id) {
        return championshipRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Championship not found : " + id));
    }

    @Transactional
    public void delete(Long userId) {
        Championship championship = findById(userId);
        championshipRepository.delete(championship);
    }

    @Transactional
    public Championship update(Championship championship) {
        Championship championshipExist = findById(championship.getId());
        championship.setId(championshipExist.getId());
        return championshipRepository.save(championship);
    }

    public Championship addTeam(Long idChampionship, List<Long> teamsIds) {
        Championship championshipEntity = findById(idChampionship);

        List<Team> teams = teamRepository.findByIdIn(teamsIds);

        championshipEntity.getTeams().addAll(teams);

        return championshipRepository.save(championshipEntity);

    }
}
