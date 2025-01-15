package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.topic.TopicRequestDto;
import com.devalrykemes.forumhub.domain.topic.TopicResponseDto;
import com.devalrykemes.forumhub.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<TopicResponseDto> createTopic(@RequestBody TopicRequestDto topicRequestDto, UriComponentsBuilder uriBuilder) {
        TopicResponseDto topicResponseDto = topicService.createTopic(topicRequestDto);
        URI uri = uriBuilder.path("/topic/{id}").buildAndExpand(topicResponseDto.id()).toUri();
        return ResponseEntity.created(uri).body(topicResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDto> getTopic(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TopicResponseDto> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopicById(id);
        return ResponseEntity.noContent().build();
    }
}
