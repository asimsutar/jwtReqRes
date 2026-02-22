package com.api.exception;
import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<APIError> handleUsernameNotFoundException(UsernameNotFoundException ex){
		APIError apiError = new APIError("Username Not found: "+ex.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<APIError>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<APIError> handleAuthenticationException(AuthenticationException ex){
		APIError apiError = new APIError("Authentication Failed: "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<APIError>(apiError, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(JwtException.class)
	public ResponseEntity<APIError> handleJWTException(JwtException ex){
		APIError apiError = new APIError("Invalid JWT Token: "+ex.getMessage(), HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<APIError>(apiError, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<APIError> handleAccessDeniedException(AccessDeniedException ex){
		APIError apiError = new APIError("Access Denied: Insufficient permissions: "+ex.getMessage(), HttpStatus.FORBIDDEN);
		return new ResponseEntity<APIError>(apiError, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIError> handleGlobalException(Exception ex){
		APIError apiError = new APIError("An Unexpected Error Occured:: "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<APIError>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
