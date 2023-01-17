package com.smart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.smart.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

//@Query(value = "SELECT u FROM USERP u where u.email=:emailaddress", nativeQuery = true)
	User findByEmail(String email);
}
