package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.course.Course;
import com.devalrykemes.forumhub.domain.course.CourseRequestDto;
import com.devalrykemes.forumhub.domain.course.CourseResponseDto;
import com.devalrykemes.forumhub.domain.course.CourseUpdateDto;
import com.devalrykemes.forumhub.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<CourseResponseDto> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).map(CourseResponseDto::new);
    }

    public CourseResponseDto getCourseById(Long id) {
        return new CourseResponseDto(courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + id)));
    }

    public CourseResponseDto createCourse(CourseRequestDto courseDto) {
        Course course = courseRepository.save(new Course(courseDto));
        return new CourseResponseDto(course);
    }

    public CourseResponseDto updateCourse(CourseUpdateDto courseDto) {
        courseRepository.updateCourse(courseDto.id(), courseDto.name(), courseDto.category());
        return new CourseResponseDto(courseDto.id(), courseDto.name(), courseDto.category());
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}
