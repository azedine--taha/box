package com.emailing.box;

import com.emailing.box.business.User.IUserService;
import com.emailing.box.entities.User;
import com.emailing.box.security.token.Token;
import com.emailing.box.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoxApplication {

	@Autowired
	IUserService userService;

	@Autowired
	TokenService tokenService;

	public static void main(String[] args) {
		SpringApplication.run(BoxApplication.class, args);
	}


}
