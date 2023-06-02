package com.saturno.bolao.controller;

import com.saturno.bolao.dto.UserRecordDto;
import com.saturno.bolao.entity.User;
import com.saturno.bolao.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid UserRecordDto userRecordDto) {
        if (userService.existsUsername(userRecordDto.username())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Username is already in use!");
        }
        if (userService.existsEmail(userRecordDto.email())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRecordDto.convertToUser()));
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<User> users = userService.findAll(pageable);

        if (!users.isEmpty()) {
            for (User user : users.getContent()) {
                user.add(linkTo(methodOn(UserController.class).getAllUsers(pageable)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") Long id) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.delete(userOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                       @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<User> userOptional = userService.findById(id);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userRecordDto.convertToUser();
        user.setId(userOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }

}
