package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.comment.CommentRequestDto;
import com.devalrykemes.forumhub.domain.comment.CommentResponseDto;
import com.devalrykemes.forumhub.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@SecurityRequirement(name = "bearer-key")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/forTopic")
    public ResponseEntity<List<CommentResponseDto>> getCommentsByTopicId(@RequestParam Long topicId) {
        return ResponseEntity.ok(commentService.getCommentsByTopicId(topicId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, UriComponentsBuilder uriBuilder) {
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto);
        URI uri = uriBuilder.path("/comment/{id}").buildAndExpand(commentResponseDto.id()).toUri();
        return ResponseEntity.created(uri).body(commentResponseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = (String) authentication.getPrincipal();
        System.out.println(userEmail);
        commentService.deleteCommentbyId(id);
        return ResponseEntity.noContent().build();
    }
}
