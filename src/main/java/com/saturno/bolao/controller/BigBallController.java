package com.saturno.bolao.controller;

import com.saturno.bolao.dto.BigBallRecordDto;
import com.saturno.bolao.entity.BigBall;
import com.saturno.bolao.service.BigBallService;
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
@RequestMapping("/bigBalls")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BigBallController {

    private final BigBallService bigBallService;

    public BigBallController(BigBallService bigBallService) {
        this.bigBallService = bigBallService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BigBallRecordDto bigBallRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bigBallService.save(bigBallRecordDto.convertToBigBall()));
    }

    @GetMapping
    public ResponseEntity<Page<BigBall>> getAllBigBalls(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<BigBall> bigBalls = bigBallService.findAll(pageable);

        if (!bigBalls.isEmpty()) {
            for (BigBall bigBall : bigBalls.getContent()) {
                bigBall.add(linkTo(methodOn(UserController.class).getAllUsers(pageable)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(bigBallService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BigBall> getBigBall(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bigBallService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        bigBallService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("BigBall deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody @Valid BigBallRecordDto bigBallRecordDto) {

        BigBall bigBall = bigBallRecordDto.convertToBigBall();
        bigBall.setId(id);

        return ResponseEntity.status(HttpStatus.OK).body(bigBallService.update(bigBall));
    }
}
