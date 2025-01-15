package com.devalrykemes.forumhub.domain.course;

import jakarta.validation.constraints.*;

public record CourseRequestDto(@NotEmpty @NotBlank String name, @NotEmpty @NotBlank String category) {
}
