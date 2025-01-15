package com.devalrykemes.forumhub.service;

import com.devalrykemes.forumhub.domain.course.Course;
import com.devalrykemes.forumhub.domain.course.CourseRequestDto;
import com.devalrykemes.forumhub.domain.course.CourseResponseDto;
import com.devalrykemes.forumhub.domain.course.CourseUpdateDto;
import com.devalrykemes.forumhub.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public CourseResponseDto getCourseById(Long id) throws IllegalArgumentException {
        return new CourseResponseDto(courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found: " + id)));
    }

    public CourseResponseDto createCourse(CourseRequestDto courseDto) {
        Course course = courseRepository.save(new Course(courseDto));
        return new CourseResponseDto(course);
    }

    public CourseResponseDto updateCourse(CourseUpdateDto courseDto) {
        courseRepository.updateCourse(courseDto.id(), courseDto.name(), courseDto.category());
        return this.getCourseById(courseDto.id());
    }

    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course courseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found: " + id));
    }
}
