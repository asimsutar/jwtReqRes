package com.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.Home;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/admin")
public class AdminController {
	@GetMapping
	public Home getMethodName() {
		return new Home("Admin");
	}
	
}
