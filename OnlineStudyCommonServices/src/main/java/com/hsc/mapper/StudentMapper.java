package com.hsc.mapper;

import org.springframework.stereotype.Component;

import com.hsc.dto.StudentDTO;
import com.hsc.entities.Student;

@Component
public class StudentMapper {

	public Student convertStudentModel(StudentDTO studentDto) {
		return new Student(studentDto.getStudentId(), studentDto.getStudentName(), studentDto.getStudentAge(),
				studentDto.getStudentEmail(), studentDto.getStudentUsername(), studentDto.getStudentPassword());
	}
}
