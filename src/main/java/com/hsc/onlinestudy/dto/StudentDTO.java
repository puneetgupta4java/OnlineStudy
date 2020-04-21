package com.hsc.onlinestudy.dto;

import java.util.List;

public class StudentDTO {

	private int studentId;
	private String studentName;
	private int studentAge;
	private String studentEmail;
	private String studentUsername;
	private String studentPassword;
	private List<CourseDTO> courses;

	public StudentDTO(int studentId, String studentName, int studentAge, String studentEmail, String studentUsername,
			String studentPassword, List<CourseDTO> courses) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentAge = studentAge;
		this.studentEmail = studentEmail;
		this.studentUsername = studentUsername;
		this.studentPassword = studentPassword;
		this.courses = courses;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentUsername() {
		return studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + studentAge;
		result = prime * result + ((studentEmail == null) ? 0 : studentEmail.hashCode());
		result = prime * result + studentId;
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		result = prime * result + ((studentPassword == null) ? 0 : studentPassword.hashCode());
		result = prime * result + ((studentUsername == null) ? 0 : studentUsername.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDTO other = (StudentDTO) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (studentAge != other.studentAge)
			return false;
		if (studentEmail == null) {
			if (other.studentEmail != null)
				return false;
		} else if (!studentEmail.equals(other.studentEmail))
			return false;
		if (studentId != other.studentId)
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		if (studentPassword == null) {
			if (other.studentPassword != null)
				return false;
		} else if (!studentPassword.equals(other.studentPassword))
			return false;
		if (studentUsername == null) {
			if (other.studentUsername != null)
				return false;
		} else if (!studentUsername.equals(other.studentUsername))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentDTO [studentId=" + studentId + ", studentName=" + studentName + ", studentAge=" + studentAge
				+ ", studentEmail=" + studentEmail + ", studentUsername=" + studentUsername + ", studentPassword="
				+ studentPassword + ", courses=" + courses + "]";
	}

}
