package com.adda.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adda.entity.User;
import com.adda.exception.InvalidUserException;
import com.adda.repository.UserRepository;
import com.adda.service.UserService;

@Service
public class ServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
        User oldUser=userRepository.findByEmail(user.getEmail());
        
        if(oldUser!=null){
           return null;
        }
        else{
            return userRepository.save(user);
        }
    }

    @Override
    public void removeUser(User user) {
    	user.setAccountState("INACTIVE");
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
    	if(userRepository.findByEmail(user.getEmail())!=null) {
        User oldUser = userRepository.findById(user.getId()).orElse(null);
        if(oldUser!=null)
        	return userRepository.save(user);
    	}
        return null;
		
    }

	@Override
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}
}
