package com.devalrykemes.forumhub.domain.topic;

import java.time.LocalDateTime;

public record TopicResponseDto(Long id, String profileNameCreator, String nameOfCourse, String title, String menssage, LocalDateTime createdAt) {
    public TopicResponseDto(Topic topic) {
        this(topic.getId(), topic.getCreator().getName(), topic.getCourse().getName(), topic.getTitle(), topic.getMenssage(), topic.getCreatedAt());
    }
}
