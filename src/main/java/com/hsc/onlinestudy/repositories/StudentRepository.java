package com.hsc.onlinestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hsc.onlinestudy.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
