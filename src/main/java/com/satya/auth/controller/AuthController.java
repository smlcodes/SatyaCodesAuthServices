package com.satya.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.satya.auth.jwt.JwtTokenGenerator;
import com.satya.auth.model.AuthenticationRequest;
import com.satya.auth.model.AuthenticationResponce;
import com.satya.auth.service.MyUsersDetailsService;

@RestController
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUsersDetailsService myUsersDetailsService;

	@Autowired
	JwtTokenGenerator jwtTokenGenerator;

	@RequestMapping("/dashboard")
	public String dashboard() {
		return "Welcome to Users Dashboard";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity authenticateAndCreateJwtToken(@RequestBody AuthenticationRequest req) {

		// -------1. Performing Authentication
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
		}
		// -------2. Once Authentication done, Generating Authenication Token using Userdetails

		UserDetails userDetails = myUsersDetailsService.loadUserByUsername(req.getUsername());
		String token = jwtTokenGenerator.generateToken(userDetails);
		AuthenticationResponce responce = new AuthenticationResponce(token);

		return ResponseEntity.ok(responce);
	}

}
