package com.smart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smart.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
