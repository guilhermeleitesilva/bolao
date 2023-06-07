package com.saturno.bolao.controller;

import com.saturno.bolao.dto.GameRecordDto;
import com.saturno.bolao.entity.Game;
import com.saturno.bolao.service.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid GameRecordDto gameRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.save(gameRecordDto.convertToGame()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        gameService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Game deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid GameRecordDto gameRecordDto) {

        Game game = gameRecordDto.convertToGame();
        game.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(gameService.update(game));
    }
}
