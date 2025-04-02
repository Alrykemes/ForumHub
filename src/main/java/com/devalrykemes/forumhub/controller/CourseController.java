package com.devalrykemes.forumhub.controller;

import com.devalrykemes.forumhub.domain.course.CourseRequestDto;
import com.devalrykemes.forumhub.domain.course.CourseResponseDto;
import com.devalrykemes.forumhub.domain.course.CourseUpdateDto;
import com.devalrykemes.forumhub.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    private final CourseService courseService;

    @GetMapping()
    public ResponseEntity<Page<CourseResponseDto>> getAllCourses(@PageableDefault Pageable page) {
        return ResponseEntity.ok(courseService.getAllCourses(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDto> createCourse(@RequestBody @Valid CourseRequestDto course, UriComponentsBuilder uriBuilder) {
        CourseResponseDto courseResponse = courseService.createCourse(course);
        URI uri = uriBuilder.path("/course/{id}").buildAndExpand(courseResponse.id()).toUri();
        return ResponseEntity.created(uri).body(courseResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<CourseResponseDto> updateCourse(@RequestBody @Valid CourseUpdateDto course) {
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }
}
