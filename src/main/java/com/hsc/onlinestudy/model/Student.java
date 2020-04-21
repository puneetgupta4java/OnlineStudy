package com.hsc.onlinestudy.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	
	@Id
	private int studentId;
	private String studentName;
	private int studentAge;
	private String studentEmail;
	private String studentUsername;
	private String studentPassword;

}
