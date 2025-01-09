package com.devalrykemes.forumhub.domain.user;

import com.devalrykemes.forumhub.domain.profile.Profile;

import java.util.List;
import java.util.UUID;

public record UserResponseDto(UUID id, String name, List<Profile> profiles) {
}
