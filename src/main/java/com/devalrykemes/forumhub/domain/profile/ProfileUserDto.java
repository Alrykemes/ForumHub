package com.devalrykemes.forumhub.domain.profile;

public record ProfileUserDto(Long id, String name) {
    public ProfileUserDto(Profile profile) {
        this(profile.getId(), profile.getName());
    }
}
