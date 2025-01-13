package com.devalrykemes.forumhub.repository;

import com.devalrykemes.forumhub.domain.course.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends JpaRepository<Course, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE course SET name = :name, category = :category WHERE id = :id")
    public void updateCourse(@Param("id") Long id, @Param("name") String name, @Param("category") String category);
}
