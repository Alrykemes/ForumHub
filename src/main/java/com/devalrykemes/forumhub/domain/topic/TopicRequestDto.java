package com.devalrykemes.forumhub.domain.topic;

public record TopicRequestDto(Long profileIdCreator, Long courseId, String title, String menssage) {
}