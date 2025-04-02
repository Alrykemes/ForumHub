package com.devalrykemes.forumhub.infra.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RegisterRequestDto(@NotEmpty @NotBlank String name, @NotEmpty @NotBlank @Email String email, @NotEmpty @NotBlank String password, @NotEmpty @NotBlank String confirmPassword) {
}
