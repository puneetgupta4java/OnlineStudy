package com.adda.beans;

import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

import com.adda.entity.Course;
import com.adda.entity.User;

@SuppressWarnings("serial")
@RedisHash("Student")
public class Student implements Serializable{

	private Long id;
	private User user;
	private Course course;

	public Student(Long id, User user, Course course) {
		this.id = id;
		this.user = user;
		this.course = course;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + id + ", user=" + user + ", course=" + course + "]";
	}

}
