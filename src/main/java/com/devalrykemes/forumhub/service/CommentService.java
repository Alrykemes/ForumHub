package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.comment.Comment;
import com.devalrykemes.forumhub.domain.comment.CommentRequestDto;
import com.devalrykemes.forumhub.domain.comment.CommentResponseDto;
import com.devalrykemes.forumhub.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProfileService profileService;
    private final TopicService topicService;

    public CommentResponseDto createComment(CommentRequestDto commentDto) {
        Comment newcomment = new Comment(commentDto);
        newcomment.setCreator(profileService.profileById(commentDto.profileCreatorId()));
        newcomment.setTopic(topicService.topicById(commentDto.topicId()));
        newcomment = commentRepository.save(newcomment);
        return new CommentResponseDto(newcomment);
    }

    public List<CommentResponseDto> getCommentsByTopicId(Long topicId) {
        return commentRepository.findAllByTopicId(topicId).stream().map(CommentResponseDto::new).toList();
    }

    public CommentResponseDto getCommentById(Long commentId) {
        return commentRepository.findById(commentId).map(CommentResponseDto::new).orElseThrow(() -> new EntityNotFoundException("Comment not found: " + commentId));
    }

    public void deleteCommentbyId(Long id) {
        this.getCommentById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(commentRepository.existsByCommentIdAndCreatorEmail(id, userEmail)) {
            commentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("This comment has not been created by this user");
        }
    }
}
