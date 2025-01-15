package com.devalrykemes.forumhub.domain.user;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UserUpdateDto(@Nonnull UUID id,
                            @NotEmpty @NotBlank String name,
                            @NotEmpty @NotBlank String email,
                            @NotEmpty @NotBlank String password,
                            @NotEmpty @NotBlank String confirmPassword) {
}
