package com.hsc.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsc.dto.StudentDTO;
import com.hsc.entities.Student;
import com.hsc.mapper.StudentMapper;
import com.hsc.repositories.StudentRepository;
import com.hsc.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository studentRepository;
	private StudentMapper studentMapper;

	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
	}

	@Override
	public String addstudent(StudentDTO studentDto) {
		Student student = studentMapper.convertStudentModel(studentDto);
		studentRepository.save(student);
		return "Hi, " + student.getStudentUsername() + " registered successfully";
	}

}
