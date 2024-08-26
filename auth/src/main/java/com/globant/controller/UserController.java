package com.globant.controller;

import com.globant.dto.UserDto;
import com.globant.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.globant.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    public UserController(UserService userService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsersSorted();
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/{documentNumber}")
    public ResponseEntity<UserDto> getUserByDocumentNumber(@PathVariable Long documentNumber){
        Optional<UserDto>  userDto = userService.getUserByDocumentNumber(documentNumber);
        return userDto.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{documentNumber}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long documentNumber,@Valid @RequestBody UserDto userDto){
        UserDto toUpdateUser = userService.updateUser(documentNumber, userDto);
        return ResponseEntity.ok(toUpdateUser);
    }

    @DeleteMapping("/{documentNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long documentNumber){
        userService.deleteUser(documentNumber);
        return ResponseEntity.noContent().build();
    }




}
