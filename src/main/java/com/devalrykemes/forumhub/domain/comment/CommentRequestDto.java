package com.devalrykemes.forumhub.domain.comment;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CommentRequestDto(@Nonnull Long topicId, @Nonnull Long profileCreatorId, @NotEmpty @NotBlank String menssage) {
}
