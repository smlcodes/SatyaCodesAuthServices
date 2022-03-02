package com.satya.users.repository;

 

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satya.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findByName(String username);

}
