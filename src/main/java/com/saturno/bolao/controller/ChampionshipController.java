package com.saturno.bolao.controller;

import com.saturno.bolao.dto.ChampionshipRecordDto;
import com.saturno.bolao.dto.ChampionshipTeamRecordDto;
import com.saturno.bolao.entity.Championship;
import com.saturno.bolao.service.ChampionshipService;
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
@RequestMapping("/championships")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChampionshipController {

    private final ChampionshipService championshipService;

    public ChampionshipController(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid ChampionshipRecordDto championshipRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(championshipService.save(championshipRecordDto.convertToChampionship()));
    }

    @GetMapping
    public ResponseEntity<Page<Championship>> getAllChampionships(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Championship> championships = championshipService.findAll(pageable);

        if (!championships.isEmpty()) {
            for (Championship championship : championships.getContent()) {
                championship.add(linkTo(methodOn(ChampionshipController.class).getAllChampionships(pageable)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(championshipService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Championship> getChampionship(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(championshipService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        championshipService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Championship deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid ChampionshipRecordDto championshipRecordDto) {

        Championship championship = championshipRecordDto.convertToChampionship();
        championship.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(championshipService.update(championship));
    }

    @PutMapping("/{id}/teams")
    public ResponseEntity<Object> addTeams(@PathVariable(value = "id") Long idChampionship,
                                         @RequestBody @Valid ChampionshipTeamRecordDto championshipTeamRecordDto) {

        return ResponseEntity.status(HttpStatus.OK).body(championshipService.addTeam(idChampionship, championshipTeamRecordDto.teamsIds()));
    }
}
