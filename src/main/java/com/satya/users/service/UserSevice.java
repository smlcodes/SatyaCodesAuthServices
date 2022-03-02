package com.satya.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.satya.users.repository.UserRepository;

@Service
public class UserSevice {

	@Autowired
	UserRepository repository;
	
	
	
	
}
