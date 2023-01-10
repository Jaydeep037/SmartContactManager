package com.smart.service;

import org.springframework.stereotype.Service;

import com.smart.entities.User;

@Service
public interface UserService {

	User createUser(User user);
	void deleteUser(Integer userId);
}
