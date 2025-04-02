package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.course.Course;
import com.devalrykemes.forumhub.domain.course.CourseResponseDto;
import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.domain.profile.ProfileResponseDto;
import com.devalrykemes.forumhub.domain.topic.Topic;
import com.devalrykemes.forumhub.domain.topic.TopicRequestDto;
import com.devalrykemes.forumhub.domain.topic.TopicResponseDto;
import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.repository.CommentRepository;
import com.devalrykemes.forumhub.repository.ProfileRepository;
import com.devalrykemes.forumhub.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    public final TopicRepository topicRepository;
    public final ProfileService profileService;
    public final CourseService courseService;
    public final CommentRepository commentRepository;

    public TopicResponseDto createTopic(TopicRequestDto topicRequestDto) {
        Topic newTopic = new Topic(topicRequestDto);
        newTopic.setCreator(profileService.profileById(topicRequestDto.profileIdCreator()));
        newTopic.setCourse(courseService.courseById(topicRequestDto.courseId()));
        newTopic = topicRepository.save(newTopic);
        return new TopicResponseDto(newTopic);
    }

    public TopicResponseDto getTopicById(Long id) {
        return new TopicResponseDto(topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found: " + id)));
    }

    public void deleteTopicById(Long id) {
        this.getTopicById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(topicRepository.existsByTopicIdAndCreatorEmail(id, userEmail)) {
            topicRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("This topic has not been created by this user!");
        }
    }

    public Topic topicById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found: " + id));
    }

    public TopicResponseDto topicSolvedByComment(Long topicId, Long commentId) {
        this.getTopicById(topicId);
        commentRepository.findById(commentId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        if(topicRepository.existsByTopicIdAndCreatorEmail(topicId, userEmail)) {
            topicRepository.topicIsSolved(topicId);
            commentRepository.commentResolved(commentId);
            return this.getTopicById(topicId);
        } else {
            throw new IllegalArgumentException("This topic has not been created by this user!");
        }
    }

}
