package com.devalrykemes.forumhub.domain.topic;


import com.devalrykemes.forumhub.domain.comment.CommentResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record TopicResponseDto(Long id,
                               String profileNameCreator,
                               String nameOfCourse,
                               String title,
                               @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime createdAt,
                               String menssage,
                               List<CommentResponseDto> comments) {

    public TopicResponseDto(Topic topic) {
        this(
                topic.getId(),
                topic.getCreator().getName(),
                topic.getCourse().getName(),
                topic.getTitle(),
                topic.getCreatedAt(),
                topic.getMenssage(),
                topic.getComments().stream().map(CommentResponseDto::new).toList()
        );
    }
}
