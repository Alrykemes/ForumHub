package com.devalrykemes.forumhub.domain.course;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CourseUpdateDto(@Nonnull Long id,
                              @NotEmpty @NotBlank String name,
                              @NotEmpty @NotBlank String category) {
}
