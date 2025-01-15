package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.comment.Comment;
import com.devalrykemes.forumhub.domain.comment.CommentRequestDto;
import com.devalrykemes.forumhub.domain.comment.CommentResponseDto;
import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.domain.profile.ProfileResponseDto;
import com.devalrykemes.forumhub.domain.topic.Topic;
import com.devalrykemes.forumhub.domain.topic.TopicResponseDto;
import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProfileService profileService;
    private final UserService userService;
    private final TopicService topicService;

    public CommentResponseDto createComment(CommentRequestDto commentDto) {
        Comment newcomment = new Comment(commentDto);
        newcomment.setCreator(profileService.profileById(commentDto.ProfileCreatorId()));
        newcomment.setTopic(topicService.topicById(commentDto.topicId()));
        newcomment = commentRepository.save(newcomment);

        return new CommentResponseDto(newcomment);
    }
}
