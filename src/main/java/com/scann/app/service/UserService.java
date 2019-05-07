package com.scann.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scann.app.entity.UserApp;
import com.scann.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
	@Autowired
	private UserRepository userRepository;
	
	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserApp user=userRepository.findByUsername(username);
		return new User(user.getUsername(),user.getPassword(),user.getAuthorities());
	}
	
	public List<UserApp> findAllUser(){
		return userRepository.findAll();
	}
	
	public void saveUser(UserApp user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
}
