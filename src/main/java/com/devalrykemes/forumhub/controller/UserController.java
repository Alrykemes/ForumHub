package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.user.UserRequestDto;
import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.domain.user.UserUpdateDto;
import com.devalrykemes.forumhub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;


@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> RegisterNewUser(@RequestBody @Valid UserRequestDto user, UriComponentsBuilder uriBuilder) {
        try {
            UserResponseDto userResponse = userService.createUser(user);
            URI uri = uriBuilder.path("/user/{id}").buildAndExpand(userResponse.id()).toUri();
            return ResponseEntity.created(uri).body(userResponse);
        } catch (IllegalArgumentException ex1) {
            return ResponseEntity.badRequest().body(ex1.getMessage());
        } catch (Exception ex2) {
            return ResponseEntity.badRequest().body("Not a valid request");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponseDto> UpdateUser(@RequestBody @Valid UserUpdateDto user) {
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUserById(@RequestParam UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}