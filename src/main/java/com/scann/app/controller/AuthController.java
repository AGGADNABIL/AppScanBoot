package com.scann.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scann.app.dto.LoginForm;
import com.scann.app.jwt.JwtProviderToken;
import com.scann.app.jwt.JwtResponse;
import com.scann.app.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtProviderToken jwtProvider;
	
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public JwtResponse login(@RequestBody LoginForm request) {
		
		final Authentication authentication=authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails=userService.loadUserByUsername(request.getUsername());
		String token=jwtProvider.generateToken(userDetails);
		JwtResponse response=new JwtResponse(token);
		return response ;
	}
}
