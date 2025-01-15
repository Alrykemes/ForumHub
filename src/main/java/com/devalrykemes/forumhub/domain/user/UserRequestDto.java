package com.devalrykemes.forumhub.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(@NotEmpty @NotBlank String name, @NotEmpty @NotBlank String email, @NotEmpty @NotBlank String password, @NotEmpty @NotBlank String confirmPassword) {
}
