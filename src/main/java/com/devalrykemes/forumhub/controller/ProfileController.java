package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.profile.ProfileRequestDto;
import com.devalrykemes.forumhub.domain.profile.ProfileResponseDto;
import com.devalrykemes.forumhub.domain.profile.ProfileUpdateDto;
import com.devalrykemes.forumhub.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable long id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDto> createProfile(@RequestBody ProfileRequestDto profileRequestDto, UriComponentsBuilder uriBuilder) {
        ProfileResponseDto profileResponseDto = profileService.createProfile(profileRequestDto);
        URI uri = uriBuilder.path("/{userID}/profile/{id}").buildAndExpand(profileResponseDto.idUser(), profileResponseDto.id()).toUri();
        return ResponseEntity.created(uri).body(profileResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileResponseDto> updateProfile(@RequestBody ProfileUpdateDto profileUpdateDto) {
        return ResponseEntity.ok(profileService.updateProfile(profileUpdateDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProfileResponseDto> deleteProfile(@PathVariable long id) {
        profileService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
