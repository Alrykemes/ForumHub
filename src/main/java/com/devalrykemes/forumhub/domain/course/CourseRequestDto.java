package com.devalrykemes.forumhub.domain.course;

import jakarta.validation.constraints.*;
import jakarta.annotation.Nonnull;

public record CourseRequestDto(@Nonnull @NotBlank String name, @Nonnull @NotBlank String category) {
}
