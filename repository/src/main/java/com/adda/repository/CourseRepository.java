package com.adda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adda.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findByCourseName(String courseName);
}
