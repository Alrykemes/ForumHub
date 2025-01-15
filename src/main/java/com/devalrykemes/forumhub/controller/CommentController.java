package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.comment.CommentRequestDto;
import com.devalrykemes.forumhub.domain.comment.CommentResponseDto;
import com.devalrykemes.forumhub.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<CommentResponseDto> getComments() {
        return null;
    }

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path("").buildAndExpand().toUri();
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto);
        return ResponseEntity.created(uri).body(commentResponseDto);
    }
}
