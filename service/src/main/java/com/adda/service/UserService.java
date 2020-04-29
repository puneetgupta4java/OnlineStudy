package com.adda.service;

import java.util.List;

import com.adda.entity.User;

public interface UserService {

 User getUser(String email, String password);
 User createUser(User newUser);
 void removeUser(User user);
 User updateUser(User user);
 List<User> getAllUsers();
}
