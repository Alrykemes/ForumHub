package com.devalrykemes.forumhub.domain.profile;

import java.util.UUID;

public record ProfileResponseDto(Long id, UUID idUser, String name) {
    public ProfileResponseDto(Profile profile) {
        this(profile.getId(), profile.getUserOwner().getId(), profile.getName());
    }
}
