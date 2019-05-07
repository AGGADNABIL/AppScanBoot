package com.scann.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.scann.app.entity.UserApp;
import com.scann.app.service.UserService;


@Component
public class ApplicationInitializer implements CommandLineRunner {
	
	
	private Logger logger =LoggerFactory.getLogger(ApplicationInitializer.class);
	@Autowired
	private UserService userDetailsService;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		if (userDetailsService.findAllUser().isEmpty()) {
			userDetailsService.saveUser(new UserApp("admin","admin"));
			logger.info("user created :");
		}
		//return null
	}

}
