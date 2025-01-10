package com.devalrykemes.forumhub.domain.user;

import com.devalrykemes.forumhub.domain.profile.Profile;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, String email, List<Profile> profiles) {
    public UserResponseDto (User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getProfiles());
    }
}
