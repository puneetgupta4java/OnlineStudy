package com.adda.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		User oldUser = userRepository.findByEmail(user.getEmail());

		if (oldUser != null) {
			return null;
		} else {
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
		User oldUser = userRepository.findById(user.getId()).orElse(null);
		if (oldUser != null) {
			User eUser = userRepository.findByEmail(user.getEmail());
			if (eUser != null) {
				if (eUser.getId().equals(oldUser.getId())) {
					return userRepository.save(user);
				}
			} else {
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
}
