package com.saturno.bolao.controller;

import com.saturno.bolao.dto.BetRecordDto;
import com.saturno.bolao.entity.Bet;
import com.saturno.bolao.service.BetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bets")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BetController {

    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BetRecordDto betRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(betService.save(betRecordDto.convertToBet()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bet> getBet(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(betService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        betService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Bet deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid BetRecordDto betRecordDto) {

        Bet bet = betRecordDto.convertToBet();
        bet.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(betService.update(bet));
    }
}
