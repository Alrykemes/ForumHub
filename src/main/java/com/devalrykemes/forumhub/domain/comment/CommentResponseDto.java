package com.devalrykemes.forumhub.domain.comment;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CommentResponseDto(Long id, Long topicId, String profileNameCreator, LocalDateTime createdAt, Boolean resolved, @JsonFormat(pattern = "dd/MM/yyyy HH:mm") String menssage) {
    public CommentResponseDto(Comment comment) {
        this(comment.getId(), comment.getTopic().getId(), comment.getCreator().getName(), comment.getCreatedAt(), comment.getResolved(), comment.getMenssage());
    }
}
