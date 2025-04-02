package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.domain.user.UserResponseDto;
import com.devalrykemes.forumhub.infra.dto.LoginRequestDto;
import com.devalrykemes.forumhub.infra.dto.RegisterRequestDto;
import com.devalrykemes.forumhub.infra.dto.ResponseDto;
import com.devalrykemes.forumhub.infra.security.TokenService;
import com.devalrykemes.forumhub.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody @Valid RegisterRequestDto registerRequest, UriComponentsBuilder uriBuilder) throws IllegalArgumentException {
        Optional<User> user = this.userRepository.findByEmail(registerRequest.email());

        if(user.isEmpty()) {
            if(this.userRepository.existsByEmail(registerRequest.email())) {
                throw new IllegalArgumentException("There is already a user with this email");
            }
            if (registerRequest.password().length() > 6) {
                if (registerRequest.password().equals(registerRequest.confirmPassword())) {
                    User newUser = new User();
                    newUser.setName(registerRequest.name());
                    newUser.setEmail(registerRequest.email());
                    newUser.setProfiles(new ArrayList<>());
                    newUser.setPassword(passwordEncoder.encode(registerRequest.password()));
                    newUser = this.userRepository.save(newUser);

                    String token = this.tokenService.generateToken(newUser);
                    URI uri = uriBuilder.path("/user/{id}").buildAndExpand(newUser.getId()).toUri();
                    return ResponseEntity.created(uri).body(new ResponseDto(token, newUser.getId(), newUser.getName(), newUser.getEmail()));
                } else {
                    throw new IllegalArgumentException("Passwords do not match");
                }
            } else {
                throw new IllegalArgumentException("The password must be longer");
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody @Valid LoginRequestDto loginRequest) {
        User user = this.userRepository.findByEmail(loginRequest.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDto(token,user.getId(), user.getName(), user.getEmail()));
        }
        return ResponseEntity.badRequest().build();
    }
}
