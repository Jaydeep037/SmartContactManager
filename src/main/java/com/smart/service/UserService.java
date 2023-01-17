package com.smart.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smart.entities.User;

@Service
public interface UserService {

	User createUser(User user);
	void deleteUser(Integer userId);
	String uploadImage(MultipartFile file, String path) throws IOException;
}
