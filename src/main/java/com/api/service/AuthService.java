package com.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dto.LoginRequest;
import com.api.dto.LoginResponse;
import com.api.dto.SignUpResponse;
import com.api.entity.User;
import com.api.repository.UserRepository;
import com.api.util.JwtAuthUtil;

@Service
public class AuthService {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtAuthUtil jwtAuthUtil;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public LoginResponse login(LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
														loginRequest.getPassword())
				);
		org.springframework.security.core.userdetails.User principle = 
				(org.springframework.security.core.userdetails.User) authentication.getPrincipal();
		
		User user = userRepository.findByUsername(principle.getUsername()).orElseThrow();
		
		String token = jwtAuthUtil.generateToken(user);
		
		return new LoginResponse(token, user.getId(), user.getUsername());
				
	}

	public SignUpResponse signUp(LoginRequest loginRequest) {
		User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
		
		if(user != null ) {
			throw new IllegalArgumentException("User Already Exist");
		}
		
		user =userRepository.save( User.builder()
					.username(loginRequest.getUsername())
					.password(passwordEncoder.encode(loginRequest.getPassword()))
					.build()
					);
		
		return new SignUpResponse(user.getId(), user.getUsername());
	}

}
