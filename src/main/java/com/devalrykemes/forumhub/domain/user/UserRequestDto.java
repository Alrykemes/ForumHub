package com.devalrykemes.forumhub.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(@Nonnull @NotBlank String name, @Nonnull @NotBlank String email, @Nonnull @NotBlank String password, @Nonnull @NotBlank String confirmPassword) {
}
