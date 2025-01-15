package com.devalrykemes.forumhub.domain.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record ProfileRequestDto(UUID idUser, @NotEmpty @NotBlank String name) {
}
