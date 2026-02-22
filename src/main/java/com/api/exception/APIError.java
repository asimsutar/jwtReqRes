package com.api.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data

public class APIError {
	private LocalDateTime timestamp;
	private String error;
	private HttpStatus statuscode;
	
	public APIError() {
		this.timestamp = LocalDateTime.now();
	}
	public APIError(String error, HttpStatus statuscode) {
		this();
		this.error = error;
		this.statuscode = statuscode;
	}
	
	
}