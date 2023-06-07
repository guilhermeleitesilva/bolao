package com.saturno.bolao.controller;

import com.saturno.bolao.dto.TeamRecordDto;
import com.saturno.bolao.entity.Team;
import com.saturno.bolao.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid TeamRecordDto teamRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.save(teamRecordDto.convertToTeam()));
    }

    @GetMapping
    public ResponseEntity<Page<Team>> getAllTeams(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Team> teams = teamService.findAll(pageable);

        if (!teams.isEmpty()) {
            for (Team team : teams.getContent()) {
                team.add(linkTo(methodOn(TeamController.class).getAllTeams(pageable)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(teamService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        teamService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Team deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid TeamRecordDto teamRecordDto) {

        Team team = teamRecordDto.convertToTeam();
        team.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(teamService.update(team));
    }
}
