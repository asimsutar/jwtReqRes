package com.api.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.api.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthUtil {
	
	@Value("${jwt.secretKey}")
	private String jwtSecretKey;
	
	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
	}
	
	public String generateToken(User user) {
		return Jwts.builder()
				.subject(user.getUsername())
				.claim("userId",user.getId().toString())
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + 1000*60*10))
				.signWith(getSecretKey())
				.compact();
	}

	public String getUserFromToken(String token) {
		return Jwts.parser()
				.verifyWith(getSecretKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
		
	}
	public boolean isTokenValid(String token) {
	    try {
	        Jwts.parser()
	            .verifyWith(getSecretKey())
	            .build()
	            .parse(token);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
}
