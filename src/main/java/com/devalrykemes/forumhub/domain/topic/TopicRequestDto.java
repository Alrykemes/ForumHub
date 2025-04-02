package com.devalrykemes.forumhub.domain.topic;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record TopicRequestDto(@Nonnull Long profileIdCreator,@Nonnull Long courseId, @NotEmpty @NotBlank String title, @NotEmpty @NotBlank String menssage) {
}