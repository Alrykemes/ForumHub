package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.domain.user.UserUpdateDto;
import com.devalrykemes.forumhub.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
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