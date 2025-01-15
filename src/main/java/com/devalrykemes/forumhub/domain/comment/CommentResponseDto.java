package com.devalrykemes.forumhub.domain.comment;

import java.time.LocalDateTime;

public record CommentResponseDto(Long id, Long topicId, String profileNameCreator, LocalDateTime createdAt, Boolean solved, String menssage) {
    public CommentResponseDto(Comment comment) {
        this(comment.getId(), comment.getTopic().getId(), comment.getCreator().getName(), comment.getCreatedAt(), comment.getSolved(), comment.getMenssage());
    }
}
