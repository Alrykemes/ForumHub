package com.devalrykemes.forumhub.domain.course;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;

public record CourseUpdateDto(@Nonnull Long id,
                              @Nonnull @NotBlank String name,
                              @Nonnull @NotBlank String category) {
}
