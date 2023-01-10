package com.smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smart.entities.User;
import com.smart.service.UserService;

@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/newUser")
	public ResponseEntity<User> creatUser(@RequestBody User user){
		User savedUser = this.userService.createUser(user);
		return new ResponseEntity<User>(savedUser,HttpStatus.CREATED);
	}
	
}
