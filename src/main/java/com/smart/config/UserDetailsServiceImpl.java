package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smart.entities.User;
import com.smart.repositories.UserRepo;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = this.userRepo.findByEmail(username);
			 if(user == null) {
				 throw new UsernameNotFoundException("User could not found");
		
			 }
		CustomUser customUserDetails = new CustomUser(user);
		return customUserDetails;
	}
}
