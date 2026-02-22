package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.LoginRequest;
import com.api.dto.LoginResponse;
import com.api.dto.SignUpResponse;
import com.api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.login(loginRequest));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignUpResponse> signup(@RequestBody LoginRequest loginRequest){
		return ResponseEntity.ok(authService.signUp(loginRequest));
	}
}
