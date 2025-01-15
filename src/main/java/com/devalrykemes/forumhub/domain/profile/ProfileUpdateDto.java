package com.devalrykemes.forumhub.domain.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record ProfileUpdateDto(UUID idUser, @NotEmpty @NotBlank Long id, @NotEmpty @NotBlank String name) {
}
