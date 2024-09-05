package com.globant.controller;

import com.globant.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.globant.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsersSorted();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<UserDto> getUserByDocumentNumber(@PathVariable Long documentNumber) {
        UserDto userDto = userService.getUserByDocumentNumber(documentNumber);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{documentNumber}")
    public ResponseEntity<Void> updateUser(@PathVariable Long documentNumber, @Valid @RequestBody UserDto userDto) {
        UserDto toUpdateUser = userService.updateUser(documentNumber, userDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long documentNumber) {
        userService.deleteUser(documentNumber);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/name/{firstName}")
    public ResponseEntity<List<UserDto>> getUserByFirstName(@PathVariable String firstName) {
        List<UserDto> users = userService.findUserByNameContaining(firstName);
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(users);
        }
    }


}
