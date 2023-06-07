package com.saturno.bolao.service;

import com.saturno.bolao.entity.Team;
import com.saturno.bolao.repository.TeamRepository;
import com.saturno.bolao.service.exceptions.BadRequestException;
import com.saturno.bolao.service.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public Team save(Team team) {

        if (teamRepository.existsByName(team.getName()))
            throw new BadRequestException("Conflict: Name is already in use!");

        return teamRepository.save(team);
    }

    public Page<Team> findAll(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Team not found : " + id));
    }

    @Transactional
    public void delete(Long teamId) {
        Team team = findById(teamId);
        teamRepository.delete(team);
    }

    @Transactional
    public Team update(Team user) {
        Team userExist = findById(user.getId());
        user.setId(userExist.getId());
        return teamRepository.save(user);
    }
}
