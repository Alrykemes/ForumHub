package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.domain.user.UserRequestDto;
import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity RegisterNewUser(@RequestBody @Valid UserRequestDto user, UriComponentsBuilder uriBuilder) {
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
}