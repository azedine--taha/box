package com.emailing.box;

import com.emailing.box.business.user.IRoleService;
import com.emailing.box.business.user.IUserService;
import com.emailing.box.entities.Role;
import com.emailing.box.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableSwagger2
public class BoxApplication implements CommandLineRunner {

	@Autowired
	IUserService userService;

	@Autowired
	IRoleService roleService ;

	public static void main(String[] args) {
		SpringApplication.run(BoxApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

	/*	Role roleAdmin = new Role(1L,"admin");
		Role roleUser = new Role(2L,"user");
		Role roleSuper = new Role(3L,"root");

		User user1 = new User(1L,"azedineTaha","LAMAOUAJ","AZEDINE","test","azedinetaha@gmail.com", (Stream.of(roleAdmin).collect(Collectors.toSet())));
		User user2 = new User(2L,"Taha","AZIOIZ","FOUAD","123456","fouad.azioiz@gmail.com", (Stream.of(roleUser).collect(Collectors.toSet())));


		roleService.saveRole(roleAdmin);
		roleService.saveRole(roleUser);

		userService.save(user1);
		userService.save(user2);*/
	}
}
