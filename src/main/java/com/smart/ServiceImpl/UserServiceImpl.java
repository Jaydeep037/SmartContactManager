package com.smart.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smart.entities.User;
import com.smart.repositories.UserRepo;
import com.smart.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {
		user.setRole("ROLE_USER");
		user.setEnabled(true);
		user.setImageUrl("default.png");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser=this.userRepo.save(user);
		return savedUser;
	}

	@Override
	public void deleteUser(Integer userId) {
		
	}

	@Override
	public String uploadImage(MultipartFile file, String path) throws IOException {
		
		
//		get original name
		String name=file.getOriginalFilename();
		String extension = name.substring(name.lastIndexOf("."));
		
//		generate random Id for filename
		String randomId= UUID.randomUUID().toString();
		String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		
//		Get full path
		String filePath = path + File.separator +fileName;
		
//		Create folder if not created
		
		File f = new File(filePath);
		if(!f.exists()) {
			f.mkdir();
		}
//		File copy
		Files.copy(file.getInputStream(), Paths.get(fileName));
		
		return fileName;
		
	}

}
