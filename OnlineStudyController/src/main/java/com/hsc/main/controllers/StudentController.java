package com.hsc.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.dto.StudentDTO;
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

	@PostMapping(value =  "/register", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.ALL_VALUE)
	public String registerStudent(@RequestBody StudentDTO student) {
		return studentService.addstudent(student);
	}
	
	@PostMapping("/login")
	public String loginStudent(Integer num) {
		System.out.println(num);
		return "hi";
	}
	
}
