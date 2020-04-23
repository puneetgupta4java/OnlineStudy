package com.hsc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.entities.Student;
import com.hsc.services.StudentService;

/**
 * @author puneet gupta
 *
 */
@RestController
@RequestMapping("/student")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}


	@GetMapping("/register")
	public String registerStudent(Student student) {
		return "hii";
	}
	
}
