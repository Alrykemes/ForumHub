package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.course.Course;
import com.devalrykemes.forumhub.domain.course.CourseResponseDto;
import com.devalrykemes.forumhub.domain.profile.Profile;
import com.devalrykemes.forumhub.domain.profile.ProfileResponseDto;
import com.devalrykemes.forumhub.domain.topic.Topic;
import com.devalrykemes.forumhub.domain.topic.TopicRequestDto;
import com.devalrykemes.forumhub.domain.topic.TopicResponseDto;
import com.devalrykemes.forumhub.domain.user.User;
import com.devalrykemes.forumhub.repository.ProfileRepository;
import com.devalrykemes.forumhub.repository.TopicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TopicService {

    public final TopicRepository topicRepository;
    public final ProfileService profileService;
    public final CourseService courseService;

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
        topicRepository.deleteById(id);
    }

    public Topic topicById(Long id) {
        return topicRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Topic not found: " + id));
    }

}
