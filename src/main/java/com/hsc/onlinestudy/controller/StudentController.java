package com.hsc.onlinestudy.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {

	@PostMapping("/login")
	public void studentLogin() {

	}

	@PostMapping("/register")
	public void studentRegistration() {

	}
}
