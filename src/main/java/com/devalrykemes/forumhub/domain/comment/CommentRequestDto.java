package com.devalrykemes.forumhub.domain.comment;

public record CommentRequestDto(Long topicId, Long ProfileCreatorId, String menssage) {
}
