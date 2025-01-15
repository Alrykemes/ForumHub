package com.devalrykemes.forumhub.domain.user;

import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.domain.profile.ProfileUserDto;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, List<ProfileUserDto> profiles) {
    public UserResponseDto (User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getProfiles().stream().map(ProfileUserDto::new).toList());
    }
}
