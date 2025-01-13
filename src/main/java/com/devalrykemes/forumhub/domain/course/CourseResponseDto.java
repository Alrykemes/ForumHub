package com.devalrykemes.forumhub.domain.course;

public record CourseResponseDto(Long id, String name, String category) {
    public CourseResponseDto(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
