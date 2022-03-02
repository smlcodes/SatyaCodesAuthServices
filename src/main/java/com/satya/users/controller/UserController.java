package com.satya.users.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.satya.users.model.User;
import com.satya.users.repository.UserRepository;

@RestController
@RequestMapping("/api") 
public class UserController {

	
	  @Autowired
	  UserRepository userRepository;
	  
	  
		@RequestMapping("/")
		public String dashboard() {
			return "Welcome to Users Contoller";
		}
	  
	  
	  @GetMapping("/users")
	  public ResponseEntity<List<User>> getAllUsers() {
	    try {
	    	System.out.println(" ============== i Came Here =======================");
	      List<User> users = new ArrayList<User>();	      
	      userRepository.findAll().forEach(users::add);
	       
	      if (users.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }
	      return new ResponseEntity<>(users, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  
	  @GetMapping("/users/{id}")
	  public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
	    Optional<User> userData = userRepository.findById(id);
	    if (userData.isPresent()) {
	      return new ResponseEntity<>(userData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	  @PostMapping("/users")
	  public ResponseEntity<User> createUser(@RequestBody User user) {
	    try {
	      User _user = userRepository.save(new User(user.getName(), user.getAge(), user.getAddress()));
	      return new ResponseEntity<>(_user, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  
	  @PutMapping("/users/{id}")
	  public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
	    Optional<User> userData = userRepository.findById(id);
	    if (userData.isPresent()) {
	      User _user = userData.get();
	      _user.setName(user.getName());
	      _user.setAge(user.getAge());
	      _user.setAddress(user.getAddress());
	      return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	  
	  
	  @DeleteMapping("/users/{id}")
	  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
	    try {
	      userRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @DeleteMapping("/users")
	  public ResponseEntity<HttpStatus> deleteAllUsers() {
	    try {
	      userRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
 
}
