package com.adda.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adda.beans.Student;
import com.adda.entity.Course;
import com.adda.entity.User;
import com.adda.exception.InvalidUserException;
import com.adda.redis.repository.StudentRepository;
import com.adda.repository.CourseRepository;
import com.adda.repository.UserRepository;
import com.adda.service.UserService;

@Service
public class ServiceImpl implements UserService {

	private UserRepository userRepository;

	private StudentRepository studentRepository;

	private CourseRepository courseRepository;

	@Autowired
	public ServiceImpl(UserRepository userRepository, StudentRepository studentRepository,
			CourseRepository courseRepository) {
		this.userRepository = userRepository;
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public User getUser(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		if (user != null)
			return user;
		else
			throw new InvalidUserException("Either Email or password is incorrect");
	}

	@Override
	public User createUser(User user) {
		User oldUser = userRepository.findByEmail(user.getEmail());

		if (oldUser != null) {
			return null;
		} else {
			user = userRepository.save(user);
			Course course = courseRepository.findById(1L).get();
			studentRepository.save(new Student(user.getId(), user, course));
			return user;
		}
	}

	@Override
	public void removeUser(User user) {
		user.setAccountState("INACTIVE");
		userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		User oldUser = userRepository.findById(user.getId()).orElse(null);
		Student student;
		if (oldUser != null) {
			User eUser = userRepository.findByEmail(user.getEmail());
			if (eUser != null) {
				if (eUser.getId().equals(oldUser.getId())) {
					student = studentRepository.findById(user.getId()).get();
					student.setUser(user);
					studentRepository.save(student);
					return userRepository.save(user);
				}
			} else {
				student = studentRepository.findById(user.getId()).get();
				student.setUser(user);
				studentRepository.save(student);
				return userRepository.save(user);
			}

		}
		return null;

	}

	@Override
	public Page<User> findAll(String property, String accState, Pageable pageable) {
		System.out.println("name:" + property + ", pageable content:" + pageable.getPageNumber() + " "
				+ pageable.getPageSize() + " " + pageable.getSort());
		return userRepository.findByNameOrEmail(property, accState, pageable);
	}

	@Override
	public Student getStudent(Long studentId) {
		return studentRepository.findById(studentId).get();
	}
}
