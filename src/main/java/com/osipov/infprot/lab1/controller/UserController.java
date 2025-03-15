package com.osipov.infprot.lab1.controller;

import com.osipov.infprot.lab1.dto.user.NewUserRequest;
import com.osipov.infprot.lab1.dto.user.SearchUserParams;
import com.osipov.infprot.lab1.dto.user.UpdateUserRequest;
import com.osipov.infprot.lab1.dto.user.UserDto;
import com.osipov.infprot.lab1.service.user.UserServiceAdmin;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceAdmin userService;

    @GetMapping
    public Collection<UserDto> getAllUsers(@RequestParam(required = false, defaultValue = "0") int from,
                                           @RequestParam(required = false, defaultValue = "10") int size){
        return userService.findAll(from, size);
    }

    @GetMapping("/{userId}")
    public UserDto getById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping("/search")
    public UserDto searchUser(@RequestBody SearchUserParams userParams){
        return userService.findByParam(userParams);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody @Valid NewUserRequest newUserRequest){
        return userService.create(newUserRequest);
    }

    @PutMapping
    public UserDto update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        return userService.update(updateUserRequest);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId){
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
