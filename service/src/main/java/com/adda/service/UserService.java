package com.adda.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.adda.beans.Student;
import com.adda.entity.User;

public interface UserService {

 User getUser(String email, String password);
 User createUser(User newUser);
 void removeUser(User user);
 User updateUser(User user);
 Page<User> findAll(String property, String accState, Pageable pageable);
 Student getStudent(Long studentId);
}
