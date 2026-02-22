package com.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.api.entity.User;
import com.api.repository.UserRepository;
import com.api.util.JwtAuthUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j //for log generation
public class JwtAuthFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtAuthUtil authUtil;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	HandlerExceptionResolver handlerExceptionResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			log.info("Incoming requests:{}",request.getRequestURI());
			
			String requestTokenHeader = request.getHeader("Authorization");
			
			if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			
			String token = requestTokenHeader.split("Bearer ")[1];
			if(!authUtil.isTokenValid(token)) {
				throw new RuntimeException("Invalid or expired JWT token");
			}
			
			
			String username = authUtil.getUserFromToken(token);
			
			if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
								);
				
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
				filterChain.doFilter(request, response);
				
				
			}
		}catch (Exception e) {
			SecurityContextHolder.clearContext();
			handlerExceptionResolver.resolveException(request, response, null, e);
		}
		
	}

}
