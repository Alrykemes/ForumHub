package com.devalrykemes.forumhub.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserUpdateDto(@Nonnull @NotBlank UUID id,
                            @Nonnull @NotBlank String name,
                            @Nonnull @NotBlank String email,
                            @Nonnull @NotBlank String password,
                            @Nonnull @NotBlank String confirmPassword) {
}
